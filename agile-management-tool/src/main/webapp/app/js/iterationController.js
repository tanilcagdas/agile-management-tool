app.controller('iterationCtrl', function($scope, $http) {
	var req =
	{ 
			method: 'GET',
			url: 'http://localhost:8080/agile-management-tool/webresources/iteration/list',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}
	$http(req).then(function(response) {
		var finalData = response.data;
//		finalData = finalData.replace(/\\/g, "");
//		finalData = finalData.substr(1);
//		finalData = finalData.slice(0, -1);
		$scope.iterations = finalData;
		console.log($scope.iterations)
		
	}, function errorCallback(response) {
		alert();
	    // called asynchronously if an error occurs
	    // or server returns response with an error status.
	  });
	
	var saveReq =
	{ 
			method: 'POST',
			url: 'http://localhost:8080/agile-management-tool/webresources/iteration/save',
			headers: {
			   'Content-Type': undefined,
			   'username' : 'admin'
			 },
			 data: { test: 'test' }
			}

	
	 $scope.reserve = function() {
		 $http(saveReq).then(function(response) {
				$scope.iterations = response.data;
				console.log($scope.iterations)
				
			}, function errorCallback(response) {
			    // called asynchronously if an error occurs
			    // or server returns response with an error status.
				alert();
			  });
	    };

	   
	    
});