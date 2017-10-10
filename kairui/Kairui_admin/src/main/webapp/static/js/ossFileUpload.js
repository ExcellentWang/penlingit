/**
 * Created by kedong on 2017/3/1.
 */

function OSSUploader(setting) {
    // 默认值
    var __setting__ = {
        url: '',
        policy: '',
        OSSAccessKeyId: '',
        signature: '',
        dir: '',
        container: document.getElementById('container'),
        ossfile: document.getElementById('ossfile'),
        img: document.getElementById("imgUrl"),
        browse_button: 'selectfiles',
        fileUploaded: function (filePath) {
            console.log(filePath);
        }
    };

    $.extend(__setting__, setting);

    var uploader = new plupload.Uploader({
        url: __setting__.url,
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: __setting__.browse_button,
        container: __setting__.container,
        flash_swf_url: 'lib/plupload-2.1.2/js/Moxie.swf',
        silverlight_xap_url: 'lib/plupload-2.1.2/js/Moxie.xap',
        max_file_size: '60mb', //最大只能上传15mb的文件
        prevent_duplicates: true, //不允许队列中存在重复文件
        multi_selection: false,	//不支持多选
        filters: [{//只允许上传图片和视频
            title: "file",
            extensions: "gif,jpg,jpeg,png"
        }],
        multipart_params: {
            'Filename': '${filename}',
            'key': '${filename}',
            'policy': __setting__.policy,
            'OSSAccessKeyId': __setting__.OSSAccessKeyId,
            'success_action_status': '200', //让服务端返回200,不然，默认会返回204
            'signature': __setting__.signature
        },

        init: {
            PostInit: function () {
                var ossFile = __setting__.ossfile;
                ossFile.innerHTML = '';
            },

            FilesAdded: function (up, files) {
                if (up.files.length == 1) {
                    var file = files[0];
                    var typeArray = file.type.split('/');

                    //获取文件的md5
                    fileMd5(file.getNative(), function (hash) {
                        var key = getPath(hash, file.name, __setting__.dir);
                        uploader.settings.multipart_params.key = key;
                        var html = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
                            + '<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                            + '</div';
                        $(__setting__.ossfile).html(html);
                        uploader.start();
                    });
                } else {
                    showDangerUI("只能上传一个文件！~~~~~");
                    plupload.each(files, function (file) {
                        up.removeFile(file);
                    });
                }
            },

            UploadProgress: function (up, file) {
                var d = document.getElementById(file.id);
                d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";

                var prog = d.getElementsByTagName('div')[0];
                var progBar = prog.getElementsByTagName('div')[0];
                progBar.style.width = file.percent + '%';
                progBar.setAttribute('aria-valuenow', file.percent);
            },

            FileUploaded: function (up, file, info) {
                if (info.status >= 200 || info.status < 200) {
                    // 网络地址
                    var filePath = uploader.settings.url + "/" + uploader.settings.multipart_params.key;
                    // 文件名（在OSS上的key值）
                    var fileName = uploader.settings.multipart_params.key;

                    $(__setting__.img).attr("src", filePath);

                    __setting__.fileUploaded(filePath);

                    showSuccessUI("上传文件成功！~~~~~");
                    up.removeFile(file);

                }
            },

            Error: function (up, err) {
                switch (err.code) {
                    case plupload.FILE_EXTENSION_ERROR:
                        showDangerUI("上传文件的格式错误,建议上传的格式：mp4,mov,jpg,jpeg,png");
                        break;
                    case plupload.FILE_SIZE_ERROR:
                        showDangerUI("建议上传文件大小不要超过60M");
                        break;
                    case plupload.FILE_DUPLICATE_ERROR:
                        showDangerUI("文件重复");
                        break;
                    default:
                        console.log(err);
                        break;
                }
            }
        }
    });
    uploader.init();
}

function showSuccessUI(msg) {
    //ui.notify(msg).sticky().type('alert-success').hide(5000).effect('slide');
}

function showDangerUI(msg) {
    alert(msg);
    //ui.notify(msg).sticky().type('alert-danger').hide(5000).effect('slide');
}

function getObjectURL(file) {
    var url;
    if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE
        file.select();
        url = document.selection.createRange().text;
    } else {
        url = window.URL.createObjectURL(file);
    }
    return url;
}

function getDuration(file, callback) {
    var url = getObjectURL(file);
    var video = document.createElement("VIDEO");
    video.src = url;
    $("body").append(video);
    $(video).hide(0);
    var t = setInterval(function () {
        if (video.duration > 0) {
            //获取到视频时长，关闭定时器
            clearInterval(t);
            //修改input的值
            callback(Math.ceil(video.duration));
            //溢出元素
            $(video).remove();
        }
    }, 100);
}

//获取文件上传的路径
function getPath(hash, fileName, dir) {
    var timestamp = Date.parse(new Date());
    timestamp = timestamp / 1000;
    var date = new Date();
    //获取后缀名
    var suffix = fileName.match(/\.(\w+)$/);
    suffix = suffix[0];

    return dir + date.getFullYear() + '/' + (date.getMonth() + 1) + '/' + hash + suffix;
}

//获取文件的hash值
function fileMd5(file, callback) {
    var blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice;
    var chunkSize = 2097152; //读取时分割文件 按每一块2MB分割
    var chunks = Math.ceil(file.size / chunkSize);
    var currentChunk = 0;
    var spark = new SparkMD5.ArrayBuffer();

    function loadNext() {
        var fileReader = new FileReader();
        fileReader.onload = function (e) {
            spark.append(e.target.result); // 追加数组缓冲区
            currentChunk++;
            if (currentChunk < chunks) {
                loadNext();
            } else {
                callback(spark.end());
            }
        };
        fileReader.onerror = function () {
            showDangerUI("计算文件MD5出现问题");
        };
        var start = currentChunk * chunkSize,
            end = ((start + chunkSize) >= file.size) ? file.size : start + chunkSize;
        fileReader.readAsArrayBuffer(blobSlice.call(file, start, end));
    }

    loadNext();
}