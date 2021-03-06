app.service('UserService', function($http) {

	var req = {
		method : 'GET',
		url : baseUrl + path + '/webresources/user/list',
		headers : {
			'Content-Type' : 'application/json',
			'username' : 'admin'
		},
		data : {
			test : 'test'
		}
	}

	this.listAll = function(users) {
		return $http(req);
		
	};
	
	

	var saveReq = {
		method : 'POST',
		url : baseUrl + path + '/webresources/user/save',
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

//	this.owners = function() {
//		return this.users;
//	};
//
//	this.users = [
//	{
//		"username" : "admin",
//		"password" : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
//		"enabled" : true,
//		"authority" : {
//			"name" : "ROLE_ADMIN"
//		},
//		"credentialsNonExpired" : true
//	},
//	{
//		"username" : "test",
//		"password" : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
//		"enabled" : true,
//		"authority" : {
//			"name" : "ROLE_ADMIN"
//		},
//		"credentialsNonExpired" : true
//	},
//	{
//		"username" : "dev",
//		"password" : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
//		"enabled" : true,
//		"authority" : {
//			"name" : "ROLE_ADMIN"
//		},
//		"credentialsNonExpired" : true
//	}
//
//	];
//
////	 "owner":{"username":"admin","password":"a8730f269e8b5f2a6773e4d2ab6bb2eb","enabled":true,"authority":{"name":"ROLE_ADMIN"},"credentialsNonExpired":true},
////	 "startDate":1473627600000,"days":30,"endDate":1471899600000}]
//
//});
