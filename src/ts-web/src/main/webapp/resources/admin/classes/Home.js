Ext.define('Ext.app.Home',{ // 起始页
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
					style : 'padding:10px; line-height:22px;',
					html : '<center><img src = "' + Tom.School.Context.Path + '/resources/admin/classes/imags/welcome.png" width = "800" height="532"/></center>'
				} ]
			} ],
			isReLayout : false
		});
		this.callParent(arguments);
	}
});

