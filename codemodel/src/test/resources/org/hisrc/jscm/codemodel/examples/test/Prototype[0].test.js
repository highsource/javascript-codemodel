var Prototype = {
  Version: '1.6.1',
  Browser: (function () {
      var ua = navigator.userAgent;
      var isOpera = Object.prototype.toString.call(window.opera) == '[object Opera]';
      return {
        IE: !!window.attachEvent && !isOpera,
        Opera: isOpera,
        WebKit: ua.indexOf('AppleWebKit\/') > -1,
        Gecko: ua.indexOf('Gecko') > -1 && ua.indexOf('KHTML') === -1
      };
    })(),
  BrowserFeatures: {
    XPath: !!document.evaluate,
    SelectorsAPI: !!document.querySelector,
    ElementExtensions: (function () {
        var constructor = window.Element || window.HTMLElement;
        return !!(constructor && constructor.prototype);
      })(),
    SpecificElementExtensions: (function () {
        if (typeof window.HTMLDivElement !== 'undefined')
          return true;
        var div = document.createElement('div');
        var form = document.createElement('form');
        var isSupported = false;
        if (div['__proto__'] && (div['__proto__'] !== form['__proto__'])) {
          isSupported = true;
        }
        div = form = null;
        return isSupported;
      })()
  },
  ScriptFragment: '<script[^>]*>([\\S\\s]*?)<\/script>',
  emptyFunction: function () {
  },
  K: function (x) {
    return x;
  }
};