package com.tom.school.rest.controller.sys;

import static com.tom.school.core.utility.HttpResponseUtility.autorityError;
import static com.tom.school.core.utility.HttpResponseUtility.missingArugment;
import static com.tom.school.core.utility.HttpResponseUtility.write;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tom.school.core.entity.ContentType;
import com.tom.school.core.utility.HttpResponseUtility;
import com.tom.school.db.model.sys.Authority;
import com.tom.school.rest.controller.BaseController;
import com.tom.school.rest.core.Cache;
import com.tom.school.rest.service.sys.AuthorityService;

@Controller
@RequestMapping(value = "/authority")
public class AuthorityController extends BaseController<Authority> {

	private AuthorityService authorityService;

	@Resource
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
		this.service = authorityService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("name");
		String password = request.getParameter("password");
		if (userName == null || "".equals(userName) || password == null || "".equals(password)) {
			missingArugment(response);
		} else {
			if (userName.equals("tom") && password.equals("Cogent01")) {
				String token = UUID.randomUUID().toString();
				Cache.Tokens.put(token, userName);
				write(response, token, ContentType.Text_PLAIN);
			} else {
				autorityError(response);
			}
		}
	}

	@RequestMapping(value = "/getAuthority")
	public void getAuthority(HttpServletRequest request, HttpServletResponse response) {
		Short role = null;
		if (request.getParameter("globalRoleId") != null) {
			try {
				role = Short.valueOf(request.getParameter("globalRoleId"));
			} catch (Exception exception) {
				HttpResponseUtility.invalidArugment(response);
			}
		}

		List<Authority> parentAuthorityList = this.authorityService.queryByParentIdAndRole(role);
		JSONArray parentMenuJsonArray = new JSONArray();
		for (Authority parentAuthority : parentAuthorityList) {
			JSONObject parentMenuJsonObject = createMenuJsonObject(parentAuthority);

			// Add children menu
			List<Authority> childAutorityList = this.authorityService.queryChildrenByParentIdAndRole(parentAuthority.getId(), role);
			JSONArray childMenuJsonArray = new JSONArray();
			for (Authority childAuthority : childAutorityList) {
				JSONObject childMenuJsonObject = createMenuJsonObject(childAuthority);
				childMenuJsonArray.put(childMenuJsonObject);
			}
			parentMenuJsonObject.put("children", childMenuJsonArray);

			parentMenuJsonArray.put(parentMenuJsonObject);
		}
		HttpResponseUtility.write(response, parentMenuJsonArray.toString(), ContentType.JSON);
	}

	private JSONObject createMenuJsonObject(Authority authority) {
		JSONObject menuJsonObject = new JSONObject();
		menuJsonObject.put("id", authority.getId());
		menuJsonObject.put("sortOrder", authority.getSortOrder());
		menuJsonObject.put("meunCode", authority.getMenuCode());
		menuJsonObject.put("text", authority.getMenuName());
		menuJsonObject.put("menuConfig", authority.getMenuConfig());
		menuJsonObject.put("buttons", authority.getButtons());
		menuJsonObject.put("expanded", authority.getExpanded());
		menuJsonObject.put("checked", authority.getChecked());
		menuJsonObject.put("leaf", authority.getLeaf());
		menuJsonObject.put("url", authority.getUrl());
		menuJsonObject.put("iconCls", authority.getIconCls());
		return menuJsonObject;
	}

}
