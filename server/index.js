var Firebase = require("firebase");
var rest = require('restler');
var async = require('async');

var tiesRef = new Firebase("https://ties.firebaseio.com/");

var count = 0;
setInterval(function() {
  count += 1;
  rest.get('https://ties.firebaseio.com/Contacts.json').on('complete', function(data) {
    console.log(data); // auto convert to object
  });
  console.log(count);
}, 1000);
