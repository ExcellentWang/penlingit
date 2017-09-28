/**
 * Created by kedong on 2017/1/11.
 *
 * 自定义验证方法扩展
 */
$(function () {
    /**
     * 手机号验证
     */
    jQuery.validator.addMethod("mobile_number", function (value, element) {
        var result;
        var reg = /^(133|153|177|180|181|189|134|135|136|137|138|139|150|151|152|157|158|159|178|182|183|184|187|188|130|131|132|155|156|176|185|186|145|147|170)\d{8}$/;
        if (!reg.test(value)) {
            result = false;
        } else {
            result = true;
        }

        if (result) {
            return this.optional(element) || true;
        } else {
            return this.optional(element) || false;
        }

    });
});