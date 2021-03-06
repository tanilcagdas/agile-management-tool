var baseUrl = window.location.origin;
var path = window.location.pathname.substring(0, window.location.pathname
		.indexOf("/", 2));
/**
 * Main AngularJS Web Application
 */
var app = angular.module('webApp', [
  'ngRoute'  ,
  'ngAnimate',
  'ui.bootstrap'
  
]);

/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/userstory.html", controller: "PageCtrl"})
    // Pages
    .when("/iteration", {templateUrl: "partials/iteration.html", controller: "PageCtrl"})
    .when("/userstory", {templateUrl: "partials/userstory.html", controller: "PageCtrl"})
    .when("/users", {templateUrl: "partials/users.html", controller: "PageCtrl"})
 
    // Blog

    // else 404
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
app.config(function($modalProvider) {
	  angular.extend($modalProvider.defaults, {
	    html: true
	  });
	})
/**
 * Controls the Blog
 */
app.controller('BlogCtrl', function (/* $scope, $location, $http */) {
  console.log("Blog Controller reporting for duty.");
});

/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  console.log("Page Controller reporting for duty.");

  // Activates the Carousel
//  $('.carousel').carousel({
//    interval: 5000
//  });

  // Activates Tooltips for Social Links
//  $('.tooltip-social').tooltip({
//    selector: "a[data-toggle=tooltip]"
//  })
});




