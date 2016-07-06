/**
 * @class Ext.app.Home
 * @extends Ext.form.Panel
 * 起始页
 */
Ext.define('Ext.app.Home',{ 
	extend : 'Ext.form.Panel',
	initComponent : function() {
		Ext.apply(this,{
			autoScroll : true,
			defaults : {
				defaults : {
					ui : 'light',
					closable : false
				}
			},
			items : [ {
				id : 'c1',
				items : [ {
					id : 'p1',
					//title : '欢迎来到3M学校',
					style : 'padding:10px; line-height:22px;',
					html : '<center><img src = "' + Tom.School.path + '/resources/admin/classes/imags/welcome.png" width = "849" height = "565"/></center>'
				} ]
			} ],
			isReLayout : false
		});
		this.callParent(arguments);
	}
});
