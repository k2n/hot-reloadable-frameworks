---

### ホットリローダブルなDI Clojure App

    - clj-ebisu #1 2017/11/14
    - 株式会社シグニファイア代表 中村研二

---

### なぜホットリローディングしたいのか？

* REPLの起動が遅い
    * 再起動の回数を減らすしかない！|
*  Clojureの動的言語の特性を活かしたい
    * 変更をすぐに反映して確認したい! | 

---

### require :reload

* `clojure.core.require` ... ライブラリをローディング

```clojure
users=> (require 'my-ns.foo :reload)
```
* `:reload-all`は依存するライブラリをディープリロードする。
* しかし... |
    * お互いに参照するNamespaceを変更した場合、正しい順番でリロードしなければならない。|
    * 定義をファイルから削除してもメモリに残っている。|
    * `defmulti`を含むNamespaceをリロードする場合、`defmethod`もリロードする必要がある。|
    * `defprotocol`を含むNamespaceをリロードする場合、実装しているレコードなどもリロードする必要がある。|
    * マクロを含むNamespaceをリロードする場合、そのマクロを使っているNamespaceもリロードする必要がある。|
* 自分で管理するのは大変...

---

### weavejester/ns-tracker

* リロードの順番を管理してくれるライブラリ
* 後述する`clojure.tools.namespace`の一部機能を利用している。
* [Athosさんのパッチ](https://github.com/weavejester/ns-tracker/pull/17) 
* 削除したシンボルがリロード後もメモリに残っていることに注意！
* デモ|
* いちいちリロードを手動で呼ぶのはめんどくさいので...|
---

### weavejester/ring

---?code=ring-devel-demo/resources/reload.clj&lang=clojure

@[1-5]
@[21-42]
@[7-19]
 
---

### clojure.tools.namespace


