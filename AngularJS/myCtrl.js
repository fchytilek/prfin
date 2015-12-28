myApp.controller('myCtrl', function($scope,$http,$location,$routeParams) {
	//TestVariablen
		$scope.vorName = "Gerhard";
    $scope.nachName= "Schmidt";
    $scope.startDate = "2014-12-01";
    $scope.endDate= "2015-01-01";
    $scope.nameAktie="msft";
		$scope.names=null;
    
			
	//Einfach 2 Strings zusammen retourgeben
		$scope.fullName = function() {
        return $scope.vorName + " " + $scope.nachName;
    };
    
  //Testliste, zum iterieren testen
    $scope.persons=[
			{name:$scope.fullName(),country:'Austria'},
			{name:'Uli Zangerl',country:'Austria'},
			{name:'Colette Caruana',country:'Malta'},
			{name:'Wendy Wuyts',country:'Belgium'}
		];
		
	//Speichere das von YAHOO empfangene JSON Objekt auf ein JSON Objekt und greife gleich auf Quotes zu 
	
		
		
		
	//Berechnung des durchschnittlichen AdjustedClose
		$scope.durchschnAdjVal = function(){
			var durch=0;
			for(var i=0;i<$scope.names.length;i++){
				durch=(durch+Number($scope.names[i].Adj_Close));
			}
			durch = durch/$scope.names.length;
			return durch;
		}
		
		
		$scope.toggle = function() {
        $http.get("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"+$scope.nameAktie+"%22%20and%20startDate%20%3D%20%22"+$scope.startDate+"%22%20and%20endDate%20%3D%20%22"+$scope.endDate+"%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=")
    .then(function(response) {$scope.names = response.data.query.results.quote;});
    };
		
});

