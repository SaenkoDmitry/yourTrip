var app = angular.module('plunker', ['gm']);

app.controller('MainCtrl', function($scope) {
  $scope.lat = undefined;
  $scope.lng = undefined;
  
  $scope.$on('gmPlacesAutocomplete::placeChanged', function(){
      var location = $scope.autocomplete.getPlace().geometry.location;
      $scope.lat = location.lat();
      $scope.lng = location.lng();
      $scope.$apply();
  });
});