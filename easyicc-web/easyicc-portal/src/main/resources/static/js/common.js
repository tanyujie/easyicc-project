$(function () {
    // tab切换
    function tabs(tabTit, on, tabCon) {
        $(tabTit).children().click(function () {
            $(this).addClass(on).siblings().removeClass(on);
            var index = $(tabTit).children().index(this);
            $(tabCon).children().eq(index).show().siblings().hide();
        });
    };
    // 
    tabs(".i_fou_ul", "i_fou_li", ".i_fou_show");
    $(".i_fou_fr>ul>li").hide();
    $(".i_fou_fr>ul>li").eq(0).show();
    // 关于我们
    tabs(".ab_ul", "ab_li", ".ab_boot_ul");
    $(".ab_boot>ul>li").hide();
    $(".ab_boot>ul>li").eq(0).show();

    // 手机端左侧
    $(".phone_mask").click(function () {
        $(".nv_clone").hide();
        $(".nv_show").show();
        $(".nav_icon").removeClass("current");
        $(".phone_mask").stop(true, true).fadeOut(1500);
        $(".phone_nav_right").stop(true, true).animate({
            "left": "-50%"
        }, 1000);
    })
    $(".nav_icon").click(function () {
        $(this).toggleClass("current");
        if ($(this).hasClass("current")) {
            $(this).find("img").first().hide();
            $(this).find("img").last().show();
            $(".phone_mask").stop(true, true).fadeIn(800);
            $(".phone_nav_right").stop(true, true).animate({
                "left": "0"
            }, 500)
        } else {
            $(this).find("img").first().show();
            $(this).find("img").last().hide();
            $(".phone_mask").stop(true, true).fadeOut(1500);
            $(".phone_nav_right").stop(true, true).animate({
                "left": "-50%"
            }, 500)
        }
    })
    $(".phone_nav_right>ul>li>a").click(function () {
        $(this).siblings(".phone_duo").fadeToggle();
        $(this).parents("li").siblings("li").find(".phone_duo").hide();
    })
    // 加上$.fn是绑定页面元素的插件  $.是绑定jq上的插件 
    $.extend({
        adds: function (a, b, c) {
            $(a).hover(function () {
                $(this).find(b).addClass(c);
            }, function () {
                $(this).find(b).removeClass(c);
            })
        }
    })
    $.adds('.i_thr>ul>li', '.thr_tu img', 'animated jello');
    $.adds('.i_swex_data>ul>li', 'img', 'animated rubberBand');
    // 右侧滑动
    $(".i_fou_fl>ul>li").click(function () {
        var four_top = $(this).position().top;
        $(".four_line").animate({
            'top': four_top + 4
        }, 500)
    })
    // 头部
    $(window).on("scroll", function () {
        gund();
    })

    function gund() {
        var t = $(window).scrollTop();
        if (t > 0) {
            $(".header").addClass("on");
            // $(".logo_one").hide();
            // $(".logo_two").show();
        } else {
            $(".header").removeClass("on");
            // $(".logo_one").show();
            // $(".logo_two").hide();
        };
    }
    gund();
    //获取验证码
    var wait = 60;

    function countdown(timeer) {
        var $timeer = $(timeer)
        if (wait == 0) {
            $timeer.attr("disabled", null);
            $timeer.html("获取验证码")
            wait = 60;
        } else {
            $timeer.attr("disabled", true);
            $timeer.html(+wait + "秒后获取");
            wait--;
            setTimeout(function () {
                countdown(timeer)
            }, 1000);
        }
    }
    $(".sms_click").on("click", function () {
        countdown(this);
    })
    // 登录
    var window_width = $(window).width()
    if (window_width > 768) {
        $(".lo_ban_er").click(function () {
            $(".pass_sao").animate({
                'bottom': '0%',
            })
            $(".pass_sao").css({
                'position': 'relative'
            })
            $(".pass_login,.pass_pass").css({
                'bottom': '-150%',
                'position': 'absolute'
            })
        })
        // 电脑登录
        $(".lo_ban_mi").click(function () {
            $(".pass_sao").css({
                'bottom': '-150%',
                'position': 'absolute'
            })
            $(".pass_login,.pass_pass").css({
                'position': 'relative'
            })
            $(".pass_login").animate({
                'bottom': '0%',
            })
            $(".pass_pass").animate({
                'bottom': '0%',
            })
        })
        // 短信登录
        $(".duan_login").click(function () {
            $(".pass_pass").animate({
                'right': '0%',
            })
            $(".pass_login,.pass_sao").css({
                'bottom': '-150%',
                'position': 'absolute'
            })
        })

        $(".duan_login2").click(function () {
            $(".pass_pass").animate({
                'right': '0',
                'bottom': '0'
            })
            $(".pass_pass").css({
                'position': 'relative'
            })
            $(".pass_sao").css({
                'bottom': '-150%',
                'position': 'absolute'
            })
        })

        $(".zhang_logo").click(function () {
            $(".pass_pass").css({
                'right': '-150%',
                'position': 'relative'
            })
            $(".pass_login").animate({
                'bottom': '0',
            })
            $(".pass_login").css({
                'position': 'relative'
            })
        })
    } else {
        $(".duan_login").click(function () {
            $(".pass_pass").show();
            $(".pass_login").hide();
        })
        $(".zhang_logo").click(function () {
            $(".pass_pass").hide();
            $(".pass_login").show();
        })
        $(".lo_ban_er").click(function () {
            $(".pass_pass").hide();
            $(".pass_login").hide();
            $(".pass_sao").show();
        })
        $(".duan_login2").click(function () {
            $(".pass_pass").show();
            $(".pass_login").hide();
            $(".pass_sao").hide();
        })
        $(".lo_ban_mi").click(function () {
            $(".pass_pass").hide();
            $(".pass_login").show();
            $(".pass_sao").hide();
        })
    }

    // 招聘
    $(".ab_hide").slideUp();
    $(".ab_thr_boot>ul>li").click(function () {
        $(this).toggleClass("on").siblings().removeClass("on");
        $(this).find(".ab_hide").slideToggle().parents("li").siblings("li").find(".ab_hide").slideUp();
    })


})
$(function () {
    $('.ulbox>li').eq(0).addClass('lishow');
    var m = 0;
    function run() {
        timer = setInterval(function () {
            m+=1;
            if (m > 3) {
                m = 0;
            }
            $('.ulbox li').eq(m).addClass('lishow').siblings('li').removeClass('lishow')
            $('.num li').eq(m).addClass('on').siblings('li').removeClass('on')

        }, 3000)
    }
    run();
    $('#banner').mouseenter(function () {
        clearInterval(timer);
        //$('.tablr').show();
        $('.num li').mouseenter(function () {
            m = $(this).index();
            $('.ulbox li').eq(m).addClass('lishow').siblings('li').removeClass('lishow');
            $('.num li').eq(m).addClass('on').siblings('li').removeClass('on');
        });

    }).mouseleave(function () {
        run();
        //$('.tablr').hide();

    })

    //$('.tablr .tabr').click(function () {
    //    m++;
    //    if (m > 3) {
    //        m = 0;
    //    }
    //    $('.ulbox li').eq(m).addClass('lishow').siblings('li').removeClass('lishow');
    //    $('.num li').eq(m).addClass('on').siblings('li').removeClass('on');
    //})
    //$('.tablr .tabl').click(function () {
    //    m--;
    //    if (m < 0) {
    //        m = 3;
    //    }
    //    $('.ulbox li').eq(m).addClass('lishow').siblings('li').removeClass('lishow');
    //    $('.num li').eq(m).addClass('on').siblings('li').removeClass('on');
    //})

    
});

$(function () {
    var audio = document.getElementById("media");
    var audios = document.getElementById("medias");
    //add
    //$('.rob_adw_o>li').click(function () {
    //    var index = $(this).index();
    //    $(this).eq(index).children(".frr").css('display', 'none');
    //    $(this).eq(index).children(".rob_avi").css('display', 'block');
    //})

    //$('.rob_adw_o>li').eq(0).click(function () {
    //    $('.frr').css('display', 'none');
    //    $('.rob_avi').css('display', 'block');
    //})

    $(".rob_adw_o .one").click(function () {
        audios.pause();
        if (audio.paused) { //判断当前的状态是否为暂停，若是则点击播放，否则暂停
            audio.play();
        } else {
            audio.pause();
        }
     
    })
     $(".rob_adw_o .two").click(function () {
         audio.pause();
         if (audios.paused) { //判断当前的状态是否为暂停，若是则点击播放，否则暂停
             audios.play();
         } else {
             audios.pause();
         }
        
     })
})