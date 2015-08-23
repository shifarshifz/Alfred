# Alfred

![](http://s6.postimg.org/byau50h0x/icon.png)

Alfred is a single Utility console application that contains many more sub programs. 

**Alfred is a test application which i used to learn Java and RegExp**

# What can he do ?
**can**
 * build PHP Slim API Statistics from main router (APIStabilizer - STABLE)
 * generate Java constants from JSON Response (Coming soon)
 * sort movies according to IMDB Rating (Coming soon)

# Demo APIStabilizer
**From**
```PHP
<?php
    $app->get('/test/get/method',function(){
		...
	});
	$app->put('/test/put/method',function(){
		...
	});
	$app->get('/test/get/method2',function(){
		...
	});
	$app->get('/test/get/method3',function(){
		...
	});
	$app->get('/test/get/method4',function(){
		...
	});
	$app->put('/test/put/method1',function(){
		...
	});
	$app->put('/test/put/method2',function(){
		...
	});
	$app->put('/test/put/method3',function(){
		...
	});
	$app->delete('/test/delete/method4',function(){
		...
	});
	$app->delete('/test/delete/method5',function(){
		...
	});

	$app->delete('/test/delete/method6',function(){
		...
	});

	$app->delete('/test/delete/method7',function(){
		...
	});
	$app->put('/test/put/method1',function(){
		...
	});
	$app->put('/test/put/method2',function(){
		...
	});
	$app->put('/test/put/method3',function(){
		...
	});
?>
```
**Alfred will generate another file with the below content**
```
myphp.php - STATISTICS
######################

1) GET - /test/get/method
2) GET - /test/get/method2
3) GET - /test/get/method3
4) GET - /test/get/method4
5) POST - /test/post/method1
6) POST - /test/post/method2
7) POST - /test/post/method3
8) DELETE - /test/delete/method4
9) DELETE - /test/delete/method5
10) DELETE - /test/delete/method6
11) DELETE - /test/delete/method7
12) PUT - /test/put/method1
13) PUT - /test/put/method2
14) PUT - /test/put/method3


METHOD STATISTICS
#################

POST : 3 - 21%
GET : 4 - 29%
DELETE : 4 - 29%
PUT : 3 - 21%
```

The ```.exe``` version of Alfred can be downloaded from [here](https://github.com/shifarshifz/Alfred/raw/master/demo/Alfred.exe).





