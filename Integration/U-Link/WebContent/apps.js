Ext.Loader.setConfig({ 
    enabled: true
    });
 
Ext.application({
    name: 'ExcelApp',
 
    appFolder: 'js',
    
    controllers: [
                  'FileUpload'
              ],
    
    launch: function() {
        Ext.widget('fileuploadform');
    }
});