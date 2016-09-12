app.controller('userstoryCtrl', function($scope, $uibModal, $log,
		UserService, UserStoryService) {
	UserStoryService.listAll().then(function(response) {
		var finalData = response.data;
		$scope.userstories = finalData;
		console.log($scope.userstories)

	}, function errorCallback(response) {
		alert(response.status + " : " + response.statusText);
	});

	$scope.save = function(item) {
		UserStoryService.save(item).then(function(response) {
		}, function errorCallback(response) {
			alert(response.status + " : " + response.statusText);
		});
	};

	$scope.owners = UserService.owners();

	$scope.animationsEnabled = true;

	$scope.open = function(size) {

		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'partials/addUserStoryModal.html',
			controller : 'UserStoryModalInstanceCtrl',
			size : size,
			resolve : {
				owners : function() {
					return $scope.owners;
				},
				userstories : function() {
					return $scope.userstories;
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

app.controller('UserStoryModalInstanceCtrl', function($scope,
		$uibModalInstance, owners, IterationService, userstories) {

	$scope.newUserStory = {
		name : ''
	};
	$scope.owners = owners;
	res = IterationService.listAll();
	res.then(function(response) {
		var finalData = response.data;
		$scope.iterations = finalData;
		console.log(this.iterations);
	}, function errorCallback(response) {
		alert(response.status + " : " + response.statusText);
	});

	$scope.userstories = userstories;

	$scope.ok = function() {
		console.log($scope.newIteration);
		console.log($scope.iterations);
		$scope.userstories.push($scope.newUserStory);
		$uibModalInstance.close($scope.newUserStory);
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});