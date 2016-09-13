app.controller('userCtrl', function($scope, $uibModal, $log, UserService) {

	$scope.save = function(item) {
		UserService.save(item).then(function(response) {
		}, function errorCallback(response) {
			alert(response.status + " : " + response.statusText);
		});
	};

	UserService.listAll().then(function(response) {
		$scope.users = response.data;
		console.log($scope.users);
	}, function errorCallback(response) {
		alert(response.status + " : " + response.statusText);
	});

	$scope.animationsEnabled = true;

	$scope.open = function(size) {

		var modalInstance = $uibModal.open({
			animation : $scope.animationsEnabled,
			templateUrl : 'partials/addUserModal.html',
			controller : 'UserModalInstanceCtrl',
			size : size,
			resolve : {
				users : function() {
					return $scope.users;
				}
			}
		});

		modalInstance.result.then(function(newUser) {
			$scope.selected = newUser;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

});

app.controller('UserModalInstanceCtrl', function($scope, $uibModalInstance,
		users) {

	$scope.newUser = {
		username : '',
		authority : {
			name : "ROLE_ADMIN"
		},
		password : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
		enabled : true,
		credentialsNonExpired : true
	};
	$scope.users = users;

	$scope.ok = function() {
		console.log($scope.newUser);
		console.log($scope.users);
		$scope.users.push($scope.newUser);
		$uibModalInstance.close($scope.newUser);
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
});