angular.module('QuestionModule', []).
    config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/questions', { templateUrl: '/templates/question/list.html', controller: QuestionIndexController }).
        when('/questions/new', {templateUrl: '/templates/question/form.html', controller: QuestionCreationController}).
        when('/questions/:resourceId/edit', {templateUrl: '/templates/question/form.html', controller: QuestionEditController}).
        when('/questions/:resourceId', {templateUrl: '/templates/question/details.html', controller: QuestionDetailsController}).
        otherwise({redirectTo: '/questionary'});
}]);


var QuestionController = function($scope, $http, $location) {
    var indexPath = '/questions';

	$scope.destroy = function(resource) {
		if (confirm("Are you sure?")) {
			$http(resource.links.destroy).success(function() {
				$scope.questions = _.reject($scope.questions, function(element) {
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
			$scope.questions = data;
		});
	};

	$scope.get = function(resourceId) {
		$http.get(indexPath + '/' + resourceId).success(function(data) {
			$scope.question = data;
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

var QuestionIndexController = function($scope, $http, $location) {
	var self = new QuestionController($scope, $http, $location);
	self.load();
};

var QuestionEditController = function($scope, $http, $routeParams, $location) {
	var self = new QuestionController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

var QuestionCreationController = function($scope, $http, $location) {
	var self = new QuestionController($scope, $http, $location);
};

var QuestionDetailsController = function($scope, $http, $routeParams, $location) {
	var self = new QuestionController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

