if(!Tom){
	var Tom = new Object();
	Tom.School = new Object();
	Tom.School.Ready = function(readyCall){
		if(readyCall){
			readyCall();
		}
	}
}