if(!Tom){
	var Tom = new Object();
	Tom.School = new Object();
	Tom.School.onReady = function(readyCallback){
		if(readyCallback){
			readyCallback();
		}
	}
}