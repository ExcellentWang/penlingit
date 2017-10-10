//去左右两边的空格
function trim(str){
	return str.replace(/^\s+|\s+$/g,"");
}
//是否为空
function isEmpty(s){
	return /^\s*$/.test(s);
}