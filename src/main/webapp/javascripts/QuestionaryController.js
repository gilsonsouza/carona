angular.module('QuestionaryModule', []).
    config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/questionaries', { templateUrl: '/templates/questionary/list.html', controller: QuestionaryIndexController }).
        when('/questionaries/new', {templateUrl: '/templates/questionary/form.html', controller: QuestionaryCreationController}).
        when('/questionsaries/:resourceId/edit', {templateUrl: '/templates/questionary/form.html', controller: QuestionaryEditController}).
        when('/questionaries/:resourceId', {templateUrl: '/templates/questionary/details.html', controller: QuestionaryDetailsController}).
        otherwise({redirectTo: '/questionaries'});
}]);


var QuestionaryController = function($scope, $http, $location) {
    var indexPath = '/questionaries';

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
			$scope.questionaries = data;
		});
	};

	$scope.get = function(resourceId) {
		$http.get(indexPath + '/' + resourceId).success(function(data) {
			$scope.questionary = data;
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

var QuestionaryIndexController = function($scope, $http, $location) {
	var self = new QuestionaryController($scope, $http, $location);
	self.load();
};

var QuestionaryEditController = function($scope, $http, $routeParams, $location) {
	var self = new QuestionaryController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

var QuestionaryCreationController = function($scope, $http, $location) {
	var self = new QuestionaryController($scope, $http, $location);
};

var QuestionaryDetailsController = function($scope, $http, $routeParams, $location) {
	var self = new QuestionaryController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

