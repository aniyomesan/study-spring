# 課題2: Spring MVC を使った ToDo 管理API

ひな形プロジェクトをベースに、ToDo 管理APIを完成させてください。

## 仕様

### todo アイテムのリソース形式

 property |  type   | description
----------|---------|------------
 id       | number  | todo アイテムを識別するID
 title    | string  | todo アイテムの内容
 done     | boolean | 消化済みであれば true

### API 仕様

* GET /api/v1/todos
  * 全アイテムを取得する
  * request
    * query: なし
    * body: なし
  * response
    * status code
      * 200: 成功
    * body: `[<todo item>, ...]`

* GET /api/v1/todos/{id}
  * `{id}` で指定したアイテムを取得する
  * request
    * query: なし
    * body: なし
  * response
    * status code
      * 200: 成功
      * 404: 指定したIDを持つアイテムが見つからない場合
    * body: `<todo item>`

* POST /api/v1/todos
  * 指定の内容でアイテムを新規作成 (追加) する
  * request
    * query: なし
    * body: `<todo item>`
      * id は指定不要 (自動で埋められる)
  * response
    * status code
      * 200: 成功
      * 404: 指定したIDを持つアイテムが見つからない場合
    * body: `<todo item>`
      * id が埋まったオブジェクトを返す

* PUT /api/v1/todos/{id}
  * `{id}` で指定したアイテムを、指定の内容で更新する (完全置換)
  * request
    * query: なし
    * body: なし
  * response
    * status code
      * 200: 成功
      * 400: URL path で指定したIDと request body 内のIDが異なる場合
      * 404: 指定したIDを持つアイテムが見つからない場合
    * body: `<todo item>`

* DELETE /api/v1/todos/{id}
  * `{id}` で指定したアイテムを削除する
  * request
    * query: なし
    * body: なし
  * response
    * status code
      * 204: 成功
      * 404: 指定したIDを持つアイテムが見つからない場合
    * body: なし

### 仕様に対するテスト

Python スクリプトからAPIを呼び出し、仕様通りの実装かテストすることができます。

```shell
$ python3 -m venv venv
$ . venv/bin/activate
(venv) $ pip install -r requirements.txt
(venv) $ pytest .
```

テストを実行する前に `./gradlew bootRun` などで起動しておきましょう。

2回目以降は `pytest .` のみでOKです。
※プロンプトに `(venv)` がない場合、`. venv/bin/activate` も実行します。

## 発展

現実的な強固なAPIにするためには必要ですが、概念からして少し難しい話をまとめました。
腕に覚えのある (そして時間に余裕のある) 人は挑戦してみましょう。

### 楽観的ロック (Optimistic Lock)

HTTP はステートレスです。

人間がリソースを更新する場合、いったん GET で内容を取得し、その内容を編集してから
PUT または PATCH により更新することになります。
リソースの削除の際も、一般的には、内容を確認してから削除することになるでしょう。

GET から PUT/PATCH/DELETE の間は、ステートレスのため、ロックをかけ続けることはできません。
編集や確認にどれだけ時間がかかるか分からず、さらには取得後に離脱してしまう可能性すらあります。

そのため、排他的ロックではなく、楽観的ロック (Optimistic Lock) という方法がしばしば取られます。
楽観的ロックは、以下のような方法で実現できます。

1. リソースに、現在の内容から変更されていないことを確認できるようなプロパティを設けます。
   * バージョン番号だったり、リソース内容のハッシュ値だったり、UUID だったり。
2. リソースが更新されるたび、上記のプロパティを更新します。
3. PUT/PATCH/DELETE の際は、その前の GET で得た上記プロパティ値をセットで送ります。
   * 現在のリソースのプロパティ値と送られてきた値を比較し、一致していたら更新します。
   * HTTP ヘッダーの If-Match などを使って渡します。
   * そのため、プロパティ値のことを eTag と名づけたりもします。

ToDo 管理API を拡張し、楽観的ロックを実現してみましょう。
