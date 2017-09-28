<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
    <head>
        <meta charset='utf-8'>
        <title>iD</title>
        <link rel='stylesheet' href='${ctx}/map/iD.css'>

        <!-- mobile devices -->
        <meta name='viewport' content='initial-scale=1.0 maximum-scale=1.0'>
        <meta name='apple-mobile-web-app-capable' content='yes' />
        <meta name='apple-mobile-web-app-status-bar-style' content='black-translucent' />

        <!--[if !IE]>-->
        <script src='${ctx}/map/iD.js'></script>
        <!--<![endif]-->
    </head>
    <body>
        <div id='id-container'></div>
        <script>
            // google analytics
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-38039653-2']);
            _gaq.push(['_trackPageview']);
            (function() {
              var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
              ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
              var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();

            // crazyegg
            setTimeout(function(){var a=document.createElement('script');
            var b=document.getElementsByTagName('script')[0];
            a.src=document.location.protocol+'//dnn506yrbagrg.cloudfront.net/pages/scripts/0013/6714.js?'+Math.floor(new Date().getTime()/3600000);
            a.async=true;a.type='text/javascript';b.parentNode.insertBefore(a,b)}, 1);

            if (typeof iD == 'undefined' || !iD.Detect().support) {
                document.getElementById('id-container').innerHTML = 'Sorry, your browser is not currently supported. Please use Potlatch 2 to edit the map.';
                document.getElementById('id-container').className = 'unsupported';
            } else {
                var id = iD.Context();
                id.ui()(document.getElementById('id-container'));
            }
        </script>
    </body>
</html>
