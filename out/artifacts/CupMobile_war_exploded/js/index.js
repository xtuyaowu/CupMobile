/**
* Created by Administrator on 2016/11/9.
*/
    $(function(){
        $('.nav div').bind('click' , function(e){
            $('.nav div.chooseOn').removeClass('chooseOn');
            $(this).addClass('chooseOn');
        });


    });
/********************************************/
    //首页轮播图
function auto(){
    var width = $('.banner ul li img').css('width');
    $('.banner ul').animate({
        'margin-left':'-'+width
    },750 , function(e){
        $('.banner ul').css({
            'margin-left':0+'rem'
        }).children('.banner ul li').last().after($('.banner ul').children('.banner ul li').first());
    });
}
setInterval(auto , 4000);

/*************************************************/
//angularJS
var app =angular.module('app',['ngRoute']);
app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/' , {templateUrl:'views/home.html' , controller:'homeController'})
        .when('/list' , {templateUrl:'views/list.html' , controller:'listController'})
        .when('/cart' , {templateUrl:'views/cart.html' , controller:'cartController'})
        .when('/mine' , {templateUrl:'views/mine.html' , controller:'mineController'})
        .when('/star' , {templateUrl:'views/star.html' , controller:'starController'})
        .when('/order' , {templateUrl:'views/order.html' , controller:'orderController'})
        .when('/detail/:id' ,{templateUrl:'views/details.html' , controller:'detailController'})
        .when('/login' , {templateUrl:'views/login.html' , controller:'loginController'})
        .when('/register' , {templateUrl:'views/register.html' , controller:'registerController'})
        .when('/address' , {templateUrl:'views/address.html' , controller:'addressController'})
        .otherwise({redirectTo: '/'})
}]);

app.controller('listController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
    $rootScope.globalData = '商城';
    $scope.data = [];
    $rootScope.cart = '#/cart'
    changeHref($scope , $rootScope);

    var list = localStorage.getItem('defaultList');
    if(null != list && JSON.parse(list).length != 0){
        $scope.data = JSON.parse(list);
    }else{
        $http
            .get('defaultList')
            .success(function (response) {

                for(var i = 0; i <response.length ; i++){

//                                console.log(response[i].image)
                    response[i].firstImage = "images/"+response[i].cupImages.split(',')[0];
//                                console.log(response[i].firstImage);
//                                console.log(response[i]);
                }
                $scope.data = response;
                localStorage.setItem('defaultList' , JSON.stringify(response));
            }
        );
    };

    // 排序方式
    $scope.default = function(){
        $('.sort ul li span.sortChoose').removeClass('sortChoose');
        $('.sort ul li:eq(0) span').addClass('sortChoose');
        //$('.list').empty();

//                getListAjax('defaultList',"defaultList.do");
//        $("html,body").animate({scrollTop: 0}, 200);
        $scope.data = JSON.parse(localStorage.defaultList);

    }


    $scope.sort = function(){
        $('.sort ul li span.sortChoose').removeClass('sortChoose');
        $('.sort ul li:eq(1) span').addClass('sortChoose');
        //$('.list').empty();
        var defaultList = JSON.parse(localStorage.getItem('defaultList'));
        //console.log(defaultList);
        var sortList = localStorage.getItem('sortList');
        if(null == sortList || JSON.parse(sortList).length == 0){
            localStorage.sortList = JSON.stringify(defaultList.sort(
                function(a,b){
                    return b.saleNumber - a.saleNumber;
                }
            ));
        };
        //console.log(JSON.parse(sortList));
        //$("html,body").animate({scrollTop: 0}, 200);
        $scope.data = JSON.parse(localStorage.getItem('sortList'));
    }
}]).controller('homeController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
    $scope.new = [];
    $scope.hot = [];
    $scope.types = [];
    $rootScope.cart = '#/cart'
    changeHref($scope , $rootScope);

    if(null != localStorage.getItem('new') && JSON.parse(localStorage.getItem('new')).length != 0){
        $scope.new = JSON.parse(localStorage.getItem('new'));
    }
    if(null != localStorage.getItem('hot') && JSON.parse(localStorage.getItem('hot')).length != 0){
        $scope.hot = JSON.parse(localStorage.getItem('hot'));
    }
    if(null != localStorage.getItem('types') && JSON.parse(localStorage.getItem('types')).length != 0){
        $scope.types = JSON.parse(localStorage.getItem('types'));
    }


    $http
        .get('index')
        .success(function(response){
           //console.log(response);

            //$scope.new = response[0];
            //$scope.hot = response[1];
            //$scope.types = response[2];
            //localStorage.setItem('new' , JSON.stringify(response[0]));
            //localStorage.setItem('hot' , JSON.stringify(response[1]));
            //localStorage.setItem('types' , JSON.stringify(response[2]));


            $scope.new = response.newCups;
            $scope.hot = response.hotCups;
            $scope.types = response.typeCups;
            localStorage.setItem('new' , JSON.stringify(response.newCups));
            localStorage.setItem('hot' , JSON.stringify(response.hotCups));
            localStorage.setItem('types' , JSON.stringify(response.typeCups));
        });




    $rootScope.globalData = '首页';
}]).controller('cartController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
    $scope.data = '购物车';
    $scope.haveData = false;
    $scope.haveNodata = true;

    $scope.num = 0;
    $scope.total = 0;

    //$scope.chooseAll = function(sender){
    //    //alert(1);
    //
    //}
    //$('.cartContainer input[type=checkbox]').bind('change' , function(){
    //    var checked = $(this).prop('checked');
    //    console.log(checked);
    //    if(checked){
    //        $(this).prev().addClass('checkedOn');
    //    }else{
    //        $(this).prev().removeClass('checkedOn');
    //    }
    //});
    //



    $scope.allChk = function(){
        var chkLength = $('.cartList :checked').length;
        var length = $('.cartList ul li').length;
        if(chkLength == length){
            return true;
        }else{
            return false;
        }
    }

    $scope.isCheckedOn = function(){
        if($('#chkAll').prop('checked')){
            return true;
        }else{
            return false;
        }
    }

    $scope.carts = [];

    $http
        .get('cart', {params: {memberId : JSON.parse(localStorage.cupMobileUser).memberId}
        })
        .success(function(response){
            //console.log(response);
            if(response.length != 0){
                for(var i = 0; i <response.length ; i++){

//                                console.log(response[i].image)
                    response[i].firstImage = "images/"+response[i].cup.cupImages.split(',')[0];
//                                console.log(response[i].firstImage);
//                                console.log(response[i]);
                }
                $scope.carts = response;
                $scope.haveData = true;
                $scope.haveNodata = false;
                $scope.num = response.length;
                $scope.total = calcTotal(response);
                //console.log(response);
            }
        });


    function calcTotal(r){
        var total = 0;
        for(var i = 0 ; i < r.length ; i++){
            total += r[i].cupPrice * r[i].cupQuantity;
        }

        return total;
    }


    // 结算
    //    $scope.addToOrder = function(){
    //        $http
    //            .get('addOrder' , {params : {}})
    //    }


    ////全选
    //$('#chkAll').bind('change',function(e){
    //    console.log($(this).prop('ng-checked'));
    //    $('.cartList input[type=checkbox]').prop('ng-checked',$(this).prop('checked'));
    //    $(this).prop('checked',$('.cartList :checked').length==$('.cartList ul li').length);
    //});
    ///*******************************************************************************************/
    ////反选
    //$('.cartList input[type=checkbox]').bind('click', function(e) { /* Act on the event */
    //    $('#chkAll').prop('ng-checked',$('.cartList :checked').length==$('tbody tr').length);
    //});
    //
    //
    //$('.cartContainer input[type=checkbox]').bind('change' , function(){
    //    var checked = $(this).prop('ng-checked');
    //    //console.log(checked);
    //    if(checked){
    //        $(this).prev().addClass('checkedOn');
    //    }else{
    //        $(this).prev().removeClass('checkedOn');
    //    }
    //});

    $rootScope.globalData = '购物车';
}]).controller('mineController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
    //$scope.data = '我的';
    $scope.unLogin = true;
    $scope.login = false;
    $scope.cartHref = '#/cart';
    $scope.starHref = '#/star';
    $scope.orderHref = '#/order'

    $rootScope.cart = '#/cart'
    changeHref($scope , $rootScope);


    if(localStorage.cupMobileUser != null){
        $scope.unLogin = false;
        $scope.login = true;
        $scope.nickName = JSON.parse(localStorage.cupMobileUser).name;
    }

    $scope.showConfirm = function(){
        $('.confirm').show();
    }

    $scope.hideConfirm = function(){
        $('.confirm').hide();
    }

    $scope.logOut = function(){
        localStorage.removeItem('cupMobileUser');
        $scope.unLogin = true;
        $scope.login = false;
        $('.confirm').hide();
    }

    $rootScope.globalData = '我的';
}]).controller('detailController',['$scope','$http','$rootScope','$routeParams',function($scope , $http , $rootScope , $routeParams){
    $scope.data = $routeParams.id;
    $rootScope.cart = '#/cart'
    changeHref($scope , $rootScope);


    // 根据id获取杯子信息
    $http
        .get('detail', {params: {id:$routeParams.id}
        })
        .success(function(response){
            //console.log(response);
            $scope.cup = response;
            $scope.imgArr = response.cupImages.split(",");
            //console.log($scope.imgArr);
            $scope.firstImage = $scope.imgArr[0];
            //$scope.myActiveSlide = 1;
            //console.log($scope.img);
            //var imgArr = response.cupImages.split(",");
        });
    $scope.goBack = function(){
        history.go(-1);
    }

    // 加入购物车
    $scope.addToCart = function(cupId){
        if(localStorage.cupMobileUser == null){
            window.location.href = '#/login';
        } else {
            $http
                .get('addCart' , {params : {cupId:$routeParams.id , memberId : JSON.parse(localStorage.cupMobileUser).memberId}
                })
                .success(function(response){
                    //console.log(response);
                    if(response == '0'){
                        // 失败
                        $('.tips').text('加入购物车失败');
                        showTips();
                    } else {
                        // 成功
                        $('.tips').text('加入购物车成功');
                        showTips();
                    }
                })
        }
    }

    // 加入收藏
    $scope.addToStar = function(cupId){
        if(localStorage.cupMobileUser == null){
            window.location.href = '#/login';
        } else {
            $http
                .get('addStar' , {params : {cupId:$routeParams.id , memberId : JSON.parse(localStorage.cupMobileUser).memberId}
                })
                .success(function(response){
                    //console.log(response);
                    if(response == '0'){
                        // 失败
                        $('.tips').text('该商品已在收藏夹');
                        showTips();
                    } else if(response == '1') {
                        // 成功
                        $('.tips').text('收藏成功');
                        showTips();
                    } else if(response == '2'){
                        $('.tips').text('收藏失败');
                        showTips();
                    }
                })
        }
    }

    $rootScope.globalData = '商品详情';
}])
    .controller('loginController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
    //$scope.data = '登录';

        $rootScope.cart = '#/cart'
        changeHref($scope , $rootScope);

        $scope.user = {
            userName:"",
            pwd:""
        }
        $scope.login = function(){
            //var userName = $('#loginName').val();
            //var pwd = $('#pwd').val();
            if($scope.user.userName.length == 0 || $scope.user.pwd.length == 0){
                showTips();
            }else{
                //console.log($scope.user);
                $http
                    .get('login.do', {params: {userName:$scope.user.userName , pwd :$scope.user.pwd }
                    })
                    .success(function(response){
                        //console.log(response);
                        if(response.length == 0){
                            showTips();
                        }else{
                            localStorage.cupMobileUser = JSON.stringify({memberId : response.memberId , name : response.nickName});
                            window.location.href = '#/mine';
                        }

                    });
            }
        }

    $rootScope.globalData = '登录';
}]).controller('registerController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
    //$scope.data = '注册';

        $scope.regist = {
            email : '',
            pwd : '',
            nickName : '',
            tel : ''
        }
        $rootScope.cart = '#/cart'
        changeHref($scope , $rootScope);

        $scope.register = function(){
            if($scope.regist.email == ''
                || $scope.regist.pwd == ''
                || $scope.regist.nickName == ''
                || $scope.regist.tel == ''
            ){
                $('.tips')
                    .text('请将信息填写完整')
                    .fadeIn(200,function(){
                    $(this).fadeOut(3000);
                });
            } else {
                $http
                    .get('register' ,
                    {params : {email : $scope.regist.email , pwd : $scope.regist.pwd , nickName : $scope.regist.nickName , tel : $scope.regist.tel}
                    })
                    .success(function(response){
                        localStorage.cupMobileUser = JSON.stringify({memberId : response.memberId , name : response.nickName});
                        window.location.href = '#/mine';
                    })
            }
        }

    $rootScope.globalData = '注册';
}]).controller('orderController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
        $scope.data = '订单';
        $scope.haveData = false;
        $scope.haveNodata = true;

        $rootScope.cart = '#/cart'
        changeHref($scope , $rootScope);

        $scope.orders = [];
        $http
            .get('order' , {params: {memberId : JSON.parse(localStorage.cupMobileUser).memberId}
            })
            .success(function(response){
                //console.log(response);
                if(response.length != 0){
                    for(var i = 0; i <response.length ; i++){

//                                console.log(response[i].image)
                        response[i].firstImage = "images/"+response[i].cup.cupImages.split(',')[0];
                    }

                    $scope.orders = response;
                    $scope.haveData = true;
                    $scope.haveNodata = false;
                }

            })


        $scope.goBack = function(){
            history.go(-1);
        }
        $rootScope.globalData = '我的订单';
}]).controller('starController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
        $scope.data = '收藏夹';
        $scope.haveData = false;
        $scope.haveNodata = true;
        $rootScope.cart = '#/cart'
        changeHref($scope , $rootScope);

        $scope.stars = [];
        $http
            .get('star' , {params: {memberId : JSON.parse(localStorage.cupMobileUser).memberId}
            })
            .success(function(response){
                //console.log(response);
                if(response.length != 0){
                    for(var i = 0; i <response.length ; i++){

//                                console.log(response[i].image)
                        response[i].firstImage = "images/"+response[i].cup.cupImages.split(',')[0];
                    }

                    $scope.stars = response;
                    $scope.haveData = true;
                    $scope.haveNodata = false;
                }

            })

        $scope.goBack = function(){
            history.go(-1);
        }

        $scope.delStar = function(collectionId){
        //function delStar(collectionId){
            $http
                .get('delStar' , {params : {collectionId : collectionId}
                })
                .success(function(response){
                    console.log(response);
                    if(response == 0){
                        // 失败
                        return;
                    } else {
                        // 成功
                        window.location.reload();
                    }
                })
        }

        $rootScope.globalData = '我的收藏';
}])
    .controller('addressController',['$scope','$http','$rootScope',function($scope , $http , $rootScope){
        $scope.data = '结算';
        $rootScope.cart = '#/cart'
        changeHref($scope , $rootScope);

        $scope.addresses = [];

        // 获取收货信息
        $http
            .get('confirmOrder.do' , {params: {memberId : JSON.parse(localStorage.cupMobileUser).memberId}
            })
            .success(function(response){
                //console.log(response);
                $scope.addresses = response;
            });

        $scope.showAdd = function(){
            $('.addAddressContainer').show();
        };

        $scope.hideAdd = function(){
            $('.addAddressContainer').hide();
        };

        $scope.chkAdd = function(){
            var input = $('.addAddressItem input');
            console.log(input);

            for(var i = 0 ; i<input.length ; i++){
                if(i == 4 ){
                    continue;
                }
                if($(input[i]).val().length == 0){
                    $(input[i]).addClass('warn');
                    return;
                }
                if(i == 1){
                    var reg = /1[34578]\d{9}/;
                    if(!reg.test($(input[i]).val())){
                        $(input[i]).addClass('warn');
                        return;
                    }
                }

            }

            // 获取信息
            var json = {
                memberId : JSON.parse(localStorage.cupMobileUser).memberId,
                receiverName : input[0].value,
                phone : input[1].value,
                province : input[2].value,
                city : input[3].value,
                country : input[4].value,
                address : input[5].value
            }

            $http
                .get('addAddress.do' , {params : json})
                .success(function(response){
                    //console.log(response);
                    if(response == 0){
                        // 失败
                        return;
                    } else {
                        // 成功
                        $('.addAddressContainer').hide();

                        $('.addressList').append(createAddressItem(json));
                    }
                })
        }


        // 提交订单
        $scope.submit = function(){
            var input = $('.addressItem').find('input[type=radio]:checked');
            if(input.length == 0){
                $('.tips')
                    .text('请选择收货信息')
                    .fadeIn(200,function(){
                        $(this).fadeOut(3000);
                    });
                return;
            }else{
                var addressId = $('.addressItem').find('input[type=radio]:checked').attr('id');
                $http
                    .get('addOrder.do' , {params : {memberId : JSON.parse(localStorage.cupMobileUser).memberId , addressId : addressId}
                    })
                    .success(function(response){
                        //console.log(response);
                        if(response == '0'){
                            return;
                        }else{
                            window.location.href = '#/order';
                        }
                    })
            }
        }


        $rootScope.globalData = '结算';
    }])


function changeHref($scope , $rootScope){
    if(localStorage.cupMobileUser == null){
        $rootScope.cart = '#login';
        $scope.cartHref = '#/login';
        $scope.starHref = '#/login';
        $scope.orderHref = '#/login'
    }
}

function showTips(){
    $('.tips').fadeIn(200,function(){
        $(this).fadeOut(3000);
    });
}

function createAddressItem(json){
    var $div = $('<div></div>');
    $div.addClass('addressItem');

    var $label = $('<label></label>').appendTo($div);
    $('<input>').attr('type' , 'radio').appendTo($label);
    $('<span></span>').text(
        json.receiverName+" "
        +json.phone+" "
        +json.province+" "
        +json.city+" "
        +json.country
    ).appendTo($label);


    return $div;
}