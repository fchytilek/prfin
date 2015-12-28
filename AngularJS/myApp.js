var myApp = angular.module("myApp", ['ngRoute']); 

myApp.config(function($routeProvider){
	$routeProvider
		.when('/',
		{
			//controller: 'myCtrl',
			templateUrl:'start.html'
		})
		.when('/aktienkurseAbrufen',
		{
			controller: 'myCtrl',
			templateUrl:'aktienkurse.html'
		})
		.when('/sonstiges',
		{
			controller: 'myCtrl',
			templateUrl:'standpc.html'
		})
		.otherwise({ redirectTo: '/' });
});

app.directive("gerhardsDirektive", function() {
    return {
        template : "<h1>Meine erste Direktive!</h1>"
		};
});
