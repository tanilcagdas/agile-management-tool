app.service('UserService', function() {
	
	this.owners = function() {
		return this.users;
	};

	this.users = [
	{
		"username" : "admin",
		"password" : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
		"enabled" : true,
		"authority" : {
			"name" : "ROLE_ADMIN"
		},
		"credentialsNonExpired" : true
	},
	{
		"username" : "test",
		"password" : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
		"enabled" : true,
		"authority" : {
			"name" : "ROLE_ADMIN"
		},
		"credentialsNonExpired" : true
	},
	{
		"username" : "dev",
		"password" : "a8730f269e8b5f2a6773e4d2ab6bb2eb",
		"enabled" : true,
		"authority" : {
			"name" : "ROLE_ADMIN"
		},
		"credentialsNonExpired" : true
	}

	];

//	 "owner":{"username":"admin","password":"a8730f269e8b5f2a6773e4d2ab6bb2eb","enabled":true,"authority":{"name":"ROLE_ADMIN"},"credentialsNonExpired":true},
//	 "startDate":1473627600000,"days":30,"endDate":1471899600000}]

});
