var Firebase = require("firebase");
var rest = require('restler');
var async = require('async');

var tiesRef = new Firebase("https://ties.firebaseio.com/");
var base = "https://ties.firebaseio.com/Contacts"

var count = 0;
setInterval(function() {
  count += 1;
  rest.get(base+'.json').on('complete', function(data) {
    for (contact in data) {
      console.log(data[contact]);
      var seconds = ((new Date).getTime())/1000;
      if (seconds > data[contact].expDate) {
        var currRef = new Firebase(base+'/'+contact);
        currRef.update({expired: true});
      }
    }
  });
  //console.log(count);
}, 1000);
