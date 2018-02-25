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
router.get('/from/:id', function(req, res, next) {
   sql = 'SELECT * from orders where from_id='+req.param.id;
  console.log(sql);
  connection.query(sql, function (err, result) {
    if (result)
       res.json(result);
    else
       res.json('bad');
  });
});

router.get('/to/:id', function(req, res, next) {
   sql = 'SELECT * from orders where to_id='+req.param.id;
  console.log(sql);
  connection.query(sql, function (err, result) {
    if (result)
       res.json(result);
    else
       res.json('bad');
  });
});

router.post('/', function(req, res, next) {
   sql = 'INSERT into orders (from_id,to_id,status) VALUES (?,?,?)';
  console.log(sql);
  connection.query(sql,req.body.from_id,req.body.to_id,req.body.status, function (err, result) {
    if (result)
       res.json(result[0]);
    else
       res.json('bad');
  });
});

router.put('/{id}', function(req, res, next) {
   sql = 'UPDATE orders status=? where order_id=?';
  console.log(sql);
  connection.query(sql,req.body.status,req.param.id, function (err, result) {
    if (result)
       res.json(result[0]);
    else
       res.json('bad');
  });
});

module.exports = router;
