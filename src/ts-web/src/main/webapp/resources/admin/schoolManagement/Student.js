Ext.define('School.app.schoolManagement.Student',{ 
	extend : 'Ext.panel.Panel',
	html : '<iframe src= "' + Tom.School.Context.Path + '/resources/admin/schoolManagement/student.html" width="100%" height="100%" marginwidth="0" framespacing="0" marginheight="0" frameborder="0" ></iframe>',
	initComponent : function() {
		this.callParent(arguments);
	}
});