var Firebase = require("firebase");

var tiesRef = new Firebase("https://ties.firebaseio.com/");

tiesRef.set({
  'Counter': 2,
  'Contacts': {

    0: {
      name: 'Tim Hyon',
      timeLimit: 30,
      expDate: 1443434623,
      expired: false, 
      email: 'tim.c.hyon@gmail.com',
    } ,

    1: {
      name: 'Tim Hyon',
      timeLimit: 30,
      expDate: 1443434623,
      expired: false, 
      email: 'tim.c.hyon@gmail.com',
    } ,

  },
});

/*
tiesRef.child("location/city").on("value", function(snapshot) {
  alert(snapshot.val());  // Alerts "San Francisco"
});
*/
