<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
</head>
<body>
<h3>我的第一个 ftl 页面！</h3>
${name}
<#if sex ="0">
    男生
<#else >
    女生
</#if>

<#list userlist as user>
    ${user}
</#list>
</body>
</html>
