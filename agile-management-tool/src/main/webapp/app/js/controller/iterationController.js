

app.controller('iterationCtrl', function($scope, $uibModal, $log, UserService, IterationService) {
	
	function listAll(){
		res = IterationService.listAll();
		res.then(function(response) {
			var finalData = response.data;
			$scope.iterations = finalData;
			console.log(this.iterations);
		}, function errorCallback(response) {
			alert(response.status + " : " + response.statusText);
		});
	}
	
	listAll();
	
	$scope.save = function(item) {
		IterationService.save(item).then(function(response) {
		}, function errorCallback(response) {
			alert(response.status + " : " + response.statusText);
		});
	};
	
	UserService.listAll().then(function(response) {
		$scope.owners = response.data;
		console.log($scope.owners);
	}, function errorCallback(response) {
		alert(response.status + " : " + response.statusText);
	});

	$scope.animationsEnabled = true;

	$scope.open = function(size) {

		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'partials/addIterationModal.html',
			controller : 'ModalInstanceCtrl',
			size : size,
			resolve : {
				owners : function() {
					return $scope.owners;
				},
				iterations : function() {
					return $scope.iterations;
				}
			}
		});

		modalInstance.result.then(function(newIteration) {
			$scope.selected = newIteration;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

});

app.controller('ModalInstanceCtrl', function($scope, $uibModalInstance, owners,
		iterations) {

	$scope.newIteration = {
		name : '',
		startDate : 1473627600000,
		days : 30,
		endDate : 1471899600000
	};
	$scope.owners = owners;
	$scope.iterations = iterations;

	$scope.ok = function() {
		console.log($scope.newIteration);
		console.log($scope.iterations);
		$scope.iterations.push($scope.newIteration);
		$uibModalInstance.close($scope.newIteration);
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});