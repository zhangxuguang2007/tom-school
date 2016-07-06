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

var userName = "Tom";

Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.History.init();
	
	var tokenDelimiter = ':';
	
	var mainPortal = Ext.create('Ext.app.Home', {
		title : '欢迎'
	});
	
	var treePanel = Ext.create('Ext.tree.Panel', {
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
		root: {
            text: '树根',
            expanded: true,
            children: [{
                text: '节点一',
                leaf: true
            }, {
                text: '节点二',
                leaf: true
            }]
        }
	});
	
	mainTab = Ext.create('Ext.TabPanel', {
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
		items : [ mainPortal]
	});
	
	var viewport = Ext.create('Ext.Viewport', {
		layout : 'border',
		items : [ {
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
			}
		}, treePanel, mainTab]
	});
});



