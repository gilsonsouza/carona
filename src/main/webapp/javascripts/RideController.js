angular.module('RideModule', []).
    config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    	when('/riderequests', { templateUrl: '/templates/riderequest/list.html', controller: RideIndexController }).
    	when('/rides', { templateUrl: '/templates/ride/list.html', controller: RideIndexController }).
        when('/rides/new', {templateUrl: '/templates/ride/form.html', controller: RideCreationController}).
        when('/rides/:resourceId/edit', {templateUrl: '/templates/ride/form.html', controller: RideEditController}).
        when('/rides/:resourceId', {templateUrl: '/templates/ride/details.html', controller: RideDetailsController}).
        otherwise({redirectTo: '/rides'});
}]);


var RideController = function($scope, $http, $location) {
    var indexPath = '/rides';

	$scope.destroy = function(resource) {
		if (confirm("Are you sure?")) {
			$http(resource.links.destroy).success(function() {
				$scope.questionaries = _.reject($scope.questions, function(element) {
					return element.id === resource.id;
				});
			}).error($scope.error);
		}
	};

	$scope.error = function() {
		alert("Operation failed!");
	};

	$scope.load = function() {
		$http.get(indexPath).success(function(data) {
			$scope.rides = data;
		});
	};

	$scope.get = function(resourceId) {
		$http.get(indexPath + '/' + resourceId).success(function(data) {
			$scope.ride = data;
		});
	};

	$scope.create = function(resource) {
		$http.post(indexPath, resource).success(function() {
			$location.path(indexPath);
		}).error($scope.error);
	};

	$scope.update = function(resource) {
		var request = new Request(resource.links.update, resource);
		$http(request).success(function() {
			$location.path(indexPath);
		}).error($scope.error);
	};

	return $scope;
};

var RideIndexController = function($scope, $http, $location) {
	var self = new RideController($scope, $http, $location);
	self.load();
	$scope.requestRide = function(form) {
	    if (!form.$invalid) {
	      $http.post('/rides/requestride', form);
	      alert('Carona Solicitada!')
	    }
	  };
};

var RideEditController = function($scope, $http, $routeParams, $location) {
	var self = new RideController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

var RideCreationController = function($scope, $http, $location) {
	var self = new RideController($scope, $http, $location);
};

var RideDetailsController = function($scope, $http, $routeParams, $location) {
	var self = new RideController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

