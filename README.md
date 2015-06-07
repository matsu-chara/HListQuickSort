# HListQuickSort

http://jto.github.io/articles/typelevel_quicksort/
の写経をしつつ、 shapelessとは別実装のHListに載せ替えたりなど

## importについて

IntelliJでimportするときはscala-2.11フォルダがインポートされないようなので、

実行するときは、`File > New > Project From Existing Sources > "ディレクトリ選択" > SBT`
とした後に、

`Command + ;` ＞Modules(左側にある) を選択、src/main/scala-2.11で右クリックしてSourcesを押すと
、正常に認識されるみたいです₍₍ (ง´･_･`)ว ⁾⁾
