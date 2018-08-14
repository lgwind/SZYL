/**
 * 获取cookie的值
 * @param {Object} cname
 */
function getCookie(cname)
{
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++)
	{
		var c = ca[i].trim();
		if (c.indexOf(name)==0){
			return c.substring(name.length,c.length);
		}
  }
  return "";
}
/**
 * 设置cookie的值
 * @param {Object} cname
 * @param {Object} cvalue
 * @param {Object} exdays
 */
function setCookie(cname,cvalue,exdays){
    var d = new Date();
    d.setTime(d.getTime()+(exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.cookie = cname+"="+cvalue+"; "+expires;
}

/**
 * randomWord 产生任意长度随机字母数字组合
 * randomFlag-是否任意长度 min-任意长度最小位[固定位数] max-任意长度最大位
 * xuanfeng 2014-08-28
 */
/**
 * 产生任意长度随机字母数字组合
 * @param {Object} randomFlag 是否任意长度
 * @param {Object} min 任意长度最小位[固定位数]
 * @param {Object} max 任意长度最大位
 */
function randomWord(randomFlag, min, max){
    var str = "",
        range = min,
        arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
               'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
               'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
               'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
               'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
 
    // 随机产生
    if(randomFlag){
        range = Math.round(Math.random() * (max-min)) + min;
    }
    for(var i=0; i<range; i++){
        pos = Math.round(Math.random() * (arr.length-1));
        str += arr[pos];
    }
    return str;
}

function initUsernameCookie(){
	//若用户名为空,则设置username
	if(getCookie("username")==""){
		//设置随机用户名
		var username = randomWord(false, 36, 36);
		setCookie("username", username, 366);
	}
	console.log("(cookie)username="+getCookie("username"));
}
initUsernameCookie();