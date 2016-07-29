Ext.Loader.setPath('Ext.app', Tom.School.path + '/resources/admin/classes');
Ext.Loader.setPath('Ext.ux', Tom.School.path + '/resources/ext4.2/ux');

Ext.Ajax.timeout = 60000;
Ext.Loader.setConfig({
	enabled : true
});

Ext.require([ 'Ext.util.History', 'Ext.ux.statusbar.StatusBar', 'Ext.app.PortalPanel', 'Ext.ux.TabScrollerMenu', 'Ext.state.*', 'Ext.window.MessageBox', 'Ext.tip.*' ]);

var mainTab;
var globalPageSize = 20; // 全局分页大小
var globalDateColumnWidth = 160; // 全局时间列宽度

var userName = "zhangxuguang2007@126.com";

Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.History.init();

	var tokenDelimiter = ':';

	/* Header panel * */

	var personalCenterMenu = {
		xtype : 'button',
		icon : Tom.School.path + '/resources/shared/icons/user.png',
		text : '个人中心 (' + userName + ")",
		menu : [ {
			text : '修改密码',
			iconCls : 'icon_key',
			handler : function() {
				globalObject.openWindow('修改密码', 'profile.ChangePassword', 300);
			}
		}, '-', {
			text : '切换主题',
			handler : switchTheme
		}, '-', {
			text : '安全退出',
			handler : function() {
				top.location.href = Tom.School.path + '/sys/sysuser/logout';
			}
		} ]
	};

	function switchTheme() {
		function getQueryParam(name, queryString) {
			var match = RegExp(name + '=([^&]*)').exec(queryString || location.search);
			return match && decodeURIComponent(match[1]);
		}

		function hasOption(opt) {
			var s = window.location.search;
			var re = new RegExp('(?:^|[&?])' + opt + '(?:[=]([^&]*))?(?:$|[&])', 'i');
			var m = re.exec(s);
			return m ? (m[1] === undefined ? true : m[1]) : false;
		}

		var scriptTags = document.getElementsByTagName('script'), defaultTheme = 'neptune', defaultRtl = false, i = scriptTags.length, requires = [ 'Ext.toolbar.Toolbar', 'Ext.form.field.ComboBox', 'Ext.form.FieldContainer', 'Ext.form.field.Radio' ], defaultQueryString, src, theme, rtl;

		while (i--) {
			src = scriptTags[i].src;
			if (src.indexOf('include-ext.js') !== -1) {
				defaultQueryString = src.split('?')[1];
				if (defaultQueryString) {
					defaultTheme = getQueryParam('theme', defaultQueryString) || defaultTheme;
					defaultRtl = getQueryParam('rtl', defaultQueryString) || defaultRtl;
				}
				break;
			}
		}

		Ext.themeName = theme = getQueryParam('theme') || defaultTheme;

		rtl = getQueryParam('rtl') || defaultRtl;

		if (rtl.toString() === 'true') {
			requires.push('Ext.rtl.*');
			Ext.define('Ext.GlobalRtlComponent', {
				override : 'Ext.AbstractComponent',
				rtl : true
			});
		}

		Ext.require(requires);

		Ext.getBody().addCls(Ext.baseCSSPrefix + 'theme-' + Ext.themeName);

		if (Ext.isIE6 && theme === 'neptune') {
			Ext.Msg.show({
				title : 'Browser Not Supported',
				msg : 'The Neptune theme is not supported in IE6.',
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING
			});
		}

		if (hasOption('nocss3')) {
			Ext.supports.CSS3BorderRadius = false;
			Ext.getBody().addCls('x-nbr x-nlg');
		}

		function setParam(param) {
			var queryString = Ext.Object.toQueryString(Ext.apply(Ext.Object.fromQueryString(location.search), param));
			location.search = queryString;
		}

		function removeParam(paramName) {
			var params = Ext.Object.fromQueryString(location.search);

			delete params[paramName];

			location.search = Ext.Object.toQueryString(params);
		}

		var toolbar = Ext.widget({
			xtype : 'toolbar',
			border : true,
			rtl : false,
			id : 'options-toolbar',
			floating : true,
			fixed : true,
			preventFocusOnActivate : true,
			draggable : {
				constrain : true
			},
			items : [ {
				xtype : 'combo',
				rtl : false,
				width : 170,
				labelWidth : 45,
				fieldLabel : '主题',
				displayField : 'name',
				valueField : 'value',
				labelStyle : 'cursor:move;',
				margin : '0 5 0 0',
				store : Ext.create('Ext.data.Store', {
					fields : [ 'value', 'name' ],
					data : [ {
						value : 'classic',
						name : 'Classic'
					}, {
						value : 'gray',
						name : 'Gray'
					}, {
						value : 'neptune',
						name : 'Neptune'
					} ]
				}),
				value : theme,
				listeners : {
					select : function(combo) {
						var theme = combo.getValue();
						if (theme !== defaultTheme) {
							setParam({
								theme : theme
							});
						} else {
							removeParam('theme');
						}
					}
				}
			}, {
				xtype : 'button',
				rtl : false,
				hidden : !(Ext.repoDevMode || location.href.indexOf('qa.sencha.com') !== -1),
				enableToggle : true,
				pressed : rtl,
				text : 'RTL',
				margin : '0 5 0 0',
				listeners : {
					toggle : function(btn, pressed) {
						if (pressed) {
							setParam({
								rtl : true
							});
						} else {
							removeParam('rtl');
						}
					}
				}
			}, {
				xtype : 'tool',
				type : 'close',
				rtl : false,
				handler : function() {
					toolbar.destroy();
				}
			} ],

			// Extra constraint margins within default constrain region of
			// parentNode
			constraintInsets : '0 -' + (Ext.getScrollbarSize().width + 4) + ' 0 0'
		});
		toolbar.show();
		toolbar.alignTo(document.body, Ext.optionsToolbarAlign || 'tr-tr', [ (Ext.getScrollbarSize().width + 4) * (Ext.rootHierarchyState.rtl ? 1 : -1), -(document.body.scrollTop || document.documentElement.scrollTop) ]);

		var constrainer = function() {
			toolbar.doConstrain();
		};

		Ext.EventManager.onWindowResize(constrainer);
		toolbar.on('destroy', function() {
			Ext.EventManager.removeResizeListener(constrainer);
		});
	}

	var headerPanel = {
		region : 'north',
		xtype : 'container',
		height : 50,
		id : 'app-header',
		layout : {
			type : 'hbox',
			align : 'middle'
		},
		defaults : {
			xtype : 'component'
		},
		items : [ {
			html : '<img src = "' + Tom.School.path + '/resources/shared/images/logo.png" width="45" height="45" />',
			width : 55
		}, {
			id : 'app-header-title',
			html : "老猫学校",
			width : 200
		}, {  
			html : '',
			flex : 1 // 占满，从而Menu在最右端
		}, personalCenterMenu ]
	};

	/* Navigate panel * */
	
	var menuTreeStore = Ext.create('Ext.data.TreeStore', {
		autoLoad : true,
		proxy : {
			type : 'ajax',
			url : Tom.School.Context.BaseRESTUrl + 'authority/getAuthority?token=' + Tom.School.Context.Token,
			reader : {
				type : 'json',
				root : 'children'
			}
		}
	});

	var navigatePanel = Ext.create('Ext.tree.Panel', {
		id : 'menuTree',
		region : 'west',
		split : true,
		title : '功能导航',
		width : 220,
		stateId : 'appnav',
		stateful : true,
		margins : '2 0 0 0',
		collapsible : true,
		animCollapse : true,
		xtype : 'treepanel',
		rootVisible : false,
		store : menuTreeStore
	});

	/* Content panel * */

	var mainPortal = Ext.create('Ext.app.Home', {
		title : '欢迎'
	});

	contentTabPanel = Ext.create('Ext.TabPanel', {
		region : 'center',
		margins : '2 0 0 0',
		border : false,
		deferredRender : false,
		activeTab : 0,
		plugins : Ext.create('Ext.ux.TabCloseMenu', {
			closeTabText : '关闭面板',
			closeOthersTabsText : '关闭其他',
			closeAllTabsText : '关闭所有'
		}),
		items : [ mainPortal ],
		listeners : {
			tabchange : onTabChange,
			afterrender : onAfterRender
		}
	});

	function onTabChange(tabPanel, tab) {
		var tabs = [], ownerCt = tabPanel.ownerCt, oldToken, newToken;

		tabs.push(tab.id);
		tabs.push(tabPanel.id);

		while (ownerCt && ownerCt.is('tabpanel')) {
			tabs.push(ownerCt.id);
			ownerCt = ownerCt.ownerCt;
		}

		newToken = tabs.reverse().join(tokenDelimiter);
		oldToken = Ext.History.getToken();

		if (oldToken === null || oldToken.search(newToken) === -1) {
			Ext.History.add(newToken);
		}
	}

	function onAfterRender() {
		Ext.History.on('change', function(token) {
			var parts, tabPanel, length, i;

			if (token) {
				parts = token.split(tokenDelimiter);
				length = parts.length;

				for (i = 0; i < length - 1; i++) {
					Ext.getCmp(parts[i]).setActiveTab(Ext.getCmp(parts[i + 1]));
				}
			}
		});
		var activeTab1 = contentTabPanel.getActiveTab(), activeTab2 = activeTab1;
		onTabChange(activeTab1, activeTab2);
	}

	/* StatusBar * */

	var statusBar = {
		region : 'south',
		border : false,
		items : [ Ext.create('Ext.ux.StatusBar', {
			border : false,
			text : '',
			style : 'background:#3892D3;',
			defaults : {
				style : 'color:#fff;'
			},
			items : [ '->', '老猫工作室', '-', '©2016', '->', '->' ]
		}) ]
	}

	/* Render * */
	var viewport = Ext.create('Ext.Viewport', {
		layout : 'border',
		items : [ headerPanel, navigatePanel, contentTabPanel, statusBar ]
	});
});
