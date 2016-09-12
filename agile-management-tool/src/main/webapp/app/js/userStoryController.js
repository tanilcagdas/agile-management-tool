var baseUrl = window.location.origin;
var path = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

app.controller('userstoryCtrl', function($scope, $http) {
	var req =
	{ 
			method: 'GET',
			url: baseUrl+path+'/webresources/userstory/list',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}
	$http(req).then(function(response) {
		var finalData = response.data;
		$scope.userstories = finalData;
		console.log($scope.userstories)
		
	}, function errorCallback(response) {
		alert();
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	  });
	
	var saveReq =
	{ 
			method: 'POST',
			url: baseUrl+path+'/webresources/userstory/save',
			headers: {
			   'Content-Type': 'application/json',
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}

	
	 $scope.save = function(item) {
		$scope.item = item;

		saveReq.data = item;
		 $http(saveReq).then(function(response) {
//				$scope.userstories = response.data;
				console.log($scope.userstories)
				try {
					$scope.userstories.find($scope.item);
				} catch (e) {
					console.log('couldnt find');
				}
				
			}, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
				alert(response.status + " : " + response.statusText);
			  });
	    };
});