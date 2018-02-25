var express = require('express');
var router = express.Router();
var mysql = require('mysql'); 

var connection = mysql.createConnection({
  host: 'database2',
  user: 'root',
  password: 'password',
  database: 'edesia'
});

connection.connect(function(err) {
  if (err) console.log(err);
  console.log('You are now connected...');
});

/* GET users listing. */
router.get('/:id', function(req, res, next) {
   sql = 'SELECT * from users where id='+req.param.id;
  console.log(sql);
  connection.query(sql, function (err, result) {
    if (result)
       res.json(result[0]);
    else
       res.json('bad');
  });
});

router.post('/', function(req, res, next) {
   sql = 'INSERT into users (name,description,role,position,speciality) VALUES (?,?,?,?,?)';
  console.log(sql);
  connection.query(sql,req.body.name,req.body.description,req.body.role,req.body.position,req.body.speciality, function (err, result) {
    if (result)
       res.json(result[0]);
    else
       res.json('bad');
  });
});

module.exports = router;
