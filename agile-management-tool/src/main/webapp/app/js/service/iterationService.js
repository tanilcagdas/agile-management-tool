app.service('IterationService', function($http) {

	var req = {
		method : 'GET',
		url : baseUrl + path + '/webresources/iteration/list',
		headers : {
			'Content-Type' : 'application/json',
			'username' : 'admin'
		},
		data : {
			test : 'test'
		}
	}

	this.listAll = function(iterations) {
		return $http(req);
		
	};
	
	

	var saveReq = {
		method : 'POST',
		url : baseUrl + path + '/webresources/iteration/save',
		headers : {
			'Content-Type' : 'application/json',
			'username' : 'admin'
		},
		data : {
			test : 'test'
		}
	}

	this.save = function(item) {
		saveReq.data = item;
		return $http(saveReq);
	};

});
