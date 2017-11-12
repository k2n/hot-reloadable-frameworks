---

# ホットリローダブルなDI Clojure App

- clj-ebisu #1 2017/11/14
- 株式会社シグニファイア代表 中村研二

---

## なぜホットリローディングしたいのか？

- REPLの起動が遅い
    - 再起動の回数を減らすしかない！|
-  Clojureの動的言語の特性を活かしたい
    - 変更をすぐに反映して確認したい! | 

---

## require :reload

- `clojure.core.require` ... ライブラリをローディング
    - :reload-allは依存するライブラリをディープリロードする。

```clojure
users=> (require 'my-ns.foo :reload)
```


- しかし... |
- お互いに参照するNamespaceを変更した場合、正しい順番でリロードしなければならない。 |
- 定義をファイルから削除してもメモリに残っている。 |
- defmulti, defprotocol, macroを含むNamespaceをリロードする場合、それらを利用・実装しているコードもリロードしなければならない。 |
- 自分で管理するのは大変... |

---

## weavejester/ns-tracker

- リロードの順番を管理してくれるライブラリ
- 後述する`clojure.tools.namespace`の一部機能を利用している。
- [Athosさんのパッチ](https://github.com/weavejester/ns-tracker/pull/17) 
- 削除したシンボルがリロード後もメモリに残っていることに注意！
- デモ |
- いちいちリロードを手動で呼ぶのはめんどくさいので... |
---

## weavejester/ring

---?code=ring-devel-demo/resources/reload.clj&lang=clojure

@[1-5]
@[21-42]
@[7-19]
 
---

## clojure.tools.namespace

- デモ
- `defonce`が再評価されていることに注目 
- `tools.namespace`は`ns-tracker`と違い、古いコードを破棄する | 
- `def`, `defonce`でグローバルステートの管理ができない |
- ステートを管理する仕組みが必要... |

---

## Stuart Sierra's Component

- Map, またはRecordを定義し、start/stop時に操作が必要なら `component/Lifecycle` プロトコルを実装する
- Map/Recordの依存関係を記述するシステム・マップを定義する。 | 
- REPLから `start`, `reload`を呼んでコードの変更を反映させる。|
- `danielsz/system` が汎用コンポーネントを提供している。 | 

---

## Componentの使い方 (1) Map/Recordを定義


---
## Componentの使い方 (2) Componentを登録する

---
## Componentの使い方 (3) システムを起動、リロードする


