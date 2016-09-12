app.service('UserStoryService', function($http) {

	var req = {
		method : 'GET',
		url : baseUrl + path + '/webresources/userstory/list',
		headers : {
			'Content-Type' : undefined,
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
		url : baseUrl + path + '/webresources/userstory/save',
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
		$http(saveReq).then(function(response) {
			// $scope.userstories = response.data;
			console.log(this.userstories)
			try {
				this.userstories.find(this.item);
			} catch (e) {
				console.log('couldnt find');
			}

		}, function errorCallback(response) {
			alert(response.status + " : " + response.statusText);
		});
	};

});
