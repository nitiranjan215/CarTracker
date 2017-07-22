var app=angular.module("carTracker", ['chart.js']);

app.controller('carController', function($scope,$http,$filter) {
  $scope.menuNum=1;
  $scope.vin='';
//$scope.names = [{name: "Moroni", age: 50} /*,*/];
var twoHours=120*60*1000;
$scope.loadMap=function(){
  $scope.menuNum=4;
  $scope.getReadings();
  google.charts.load("current", {
          "packages":["map"],
          // Note: you will need to get a mapsApiKey for your project.
          // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
          "mapsApiKey": "AIzaSyADzNjC0LPHOABnlrtopqehoDnx5Orr1NM"
      });
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
          //alert('drawCharts - '+$scope.carReadings[0].vin);
          var markeraCustom=[];
          markeraCustom[0]=['Lat', 'Long', 'Name'];
          for(var i=1;i<=$scope.carReadings.length;i++){
            markeraCustom[i]=[$scope.carReadings[i-1].latitude,$scope.carReadings[i-1].longitude,$scope.carReadings[i-1].vin]
          }

          var data = google.visualization.arrayToDataTable(markeraCustom);

          var map = new google.visualization.Map(document.getElementById('map_div'));
          map.draw(data, {
            showTooltip: true,
            showInfoWindow: true
          });
        }

//  $scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };
};
$http({
        method : "GET",
        url : "http://localhost:8080/alerts/time/"+twoHours
    }).then(function mySuccess(response) {
        $scope.carAlerts = response.data;
    }, function myError(response) {
        alert("Error retrieving data!");
        //$scope.myWelcome = response.statusText;
    });
$http({
        method : "GET",
        url : "http://localhost:8080/vehicles"
    }).then(function mySuccess(response) {
        $scope.carList = response.data;
        $scope.selectedCarForReadings=$scope.carList[0].vin;
    }, function myError(response) {
        alert("Error retrieving data!");
        //$scope.myWelcome = response.statusText;
    });

$scope.loadGraph=function(vin){
  $http({
    method : "GET",
    url : "http://localhost:8080/readings/"+vin
  }).then(function mySuccess(response) {
    $scope.carReadings = response.data;
  }, function myError(response) {
    alert("Error retrieving data!");
    //$scope.myWelcome = response.statusText;
  });
};


$scope.loadAlerts=function(){
  alert('alert init');

};

$scope.loadCarAlerts=function(){
  //alert($scope.selectedCar+"--"+$scope.timeInterval);
  var time=$scope.timeInterval*60*1000;
  $http({
          method : "GET",
          url : "http://localhost:8080/alerts/vin?vin="+$scope.selectedCar+"&time="+time
      }).then(function mySuccess(response) {
          $scope.carAlerts = response.data;
      }, function myError(response) {
          alert("Error retrieving data!");
          //$scope.myWelcome = response.statusText;
      });
};

$scope.changed=function(){
  alert($scope.selectedCar);
};

$scope.getReadings=function(){
  var time=$scope.timeIntervalReadings*60*1000;
  //alert($scope.graphFor);
  $http({
          method : "GET",
          url : "http://localhost:8080/readings/"+$scope.selectedCarForReadings+"?time="+time
      }).then(function mySuccess(response) {
          $scope.carReadings = response.data;
          $scope.loadChart();
      }, function myError(response) {
          alert("Error retrieving data!");
          //$scope.myWelcome = response.statusText;
      });
};

$scope.loadChart=function(){
  var myLabels=[];
  var data=[];
  angular.forEach($scope.carReadings,function(value,index){
    myLabels.push($filter('date')(value.timestamp, "dd/MM/yyyy"));
    if($scope.graphFor==='1'){
        data.push(value.engineRpm);
    }else if ($scope.graphFor==='2') {
        data.push(value.fuelVolume);
    }else if($scope.graphFor==='3'){
        data.push(value.speed);
    }else{
        data.push(value.engineHp);
    }

  });
  $scope.labels = myLabels;
  $scope.data = data;
      $scope.colors = [
        { // grey
          backgroundColor: 'rgba(0,159,177,0.2)',
          pointBackgroundColor: 'rgba(0,159,177,1)',
          pointHoverBackgroundColor: 'rgba(0,159,177,1)',
          borderColor: 'rgba(0,159,177,1)',
          pointBorderColor: '#fff',
          pointHoverBorderColor: 'rgba(0,159,177,0.8)'
        }
      ];
      $scope.options = { legend: { display: false } };
}

    $scope.randomize = function () {
      $scope.data = $scope.data.map(function (data) {
        return data.map(function (y) {
          y = y + Math.random() * 10 - 5;
          return parseInt(y < 0 ? 0 : y > 100 ? 100 : y);
        });
      });
    };


});
