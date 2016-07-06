/**
 * @class Ext.app.Portal
 * @extends Object A sample portal layout application class.
 */

Ext.define('Ext.app.Portal', {

	extend : 'Ext.container.Viewport',
	requires : [ 'Ext.app.PortalPanel', 'Ext.app.PortalColumn',
			'Ext.app.GridPortlet', 'Ext.app.ChartPortlet' ],

	getTools : function() {
		return [ {
			xtype : 'tool',
			type : 'gear',
			handler : function(e, target, header, tool) {
				var portlet = header.ownerCt;
				portlet.setLoading('Loading...');
				Ext.defer(function() {
					portlet.setLoading(false);
				}, 2000);
			}
		} ];
	},

	initComponent : function() {
		Ext.apply(this, {
			id : 'app-viewport',
			layout : {
				type : 'border',
				padding : '0 5 5 5' // pad the layout from the window edges
			},
			items : [ {
				id : 'app-header',
				xtype : 'box',
				region : 'north',
				height : 40,
				html : '学校管理系统'
			}, {
				xtype : 'container',
				region : 'center',
				layout : 'border',
				items : [ {
					id : 'app-options',
					title : '选项',
					region : 'west',
					animCollapse : true,
					width : 200,
					minWidth : 150,
					maxWidth : 400,
					split : true,
					collapsible : true,
					layout : {
						type : 'accordion',
						animate : true
					},
					items : [ {
						title : '管理',
						html : '<ol><li>班级</li><li>学生</li><li>教师</li></ol>',
						autoScroll : true,
						border : false,
						iconCls : 'nav'
					}, {
						title : '设置',
						html : '<ol><li>角色</li><li>用户</li></ol>',
						border : false,
						autoScroll : true,
						iconCls : 'settings'
					} ]
				}, {
					id : 'app-portal',
					xtype : 'portalpanel',
					region : 'center',
					items : []
				} ]
			} ]
		});
		this.callParent(arguments);
	},  //initComponent end

	onPortletClose : function(portlet) {
		this.showMsg('"' + portlet.title + '" was removed');
	},

	showMsg : function(msg) {
		var el = Ext.get('app-msg'), msgId = Ext.id();

		this.msgId = msgId;
		el.update(msg).show();

		Ext.defer(this.clearMsg, 3000, this, [ msgId ]);
		
		alert(msg);
	},

	clearMsg : function(msgId) {
		if (msgId === this.msgId) {
			Ext.get('app-msg').hide();
		}
	}
});
