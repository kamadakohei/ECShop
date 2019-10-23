# 1.大学の教科書を売買するサイト

自分の所属している大学(および学部）を登録することで講義使用されている本を送料なしで
安く買える。（メルカリと違い、売る側にメリットがある）
自分の大学でも他の大学学部でも自由に買える



- メリット

  - 専門サイトなので余計な情報がない

  - メルカリと違い、大学内での受け渡しが可能（送料がかからない）

  



- デメリット

 - 買う側はメルカリに比べると少し高いかも。。





## 要件定義

### 必須機能

- 一覧表示機能（出品されている本の一覧、写真と本の名前、料金、いいねの数みれる）
- 詳細表示機能（出品者、いいねの数、写真、本の名前）
  ＋ amazonでの相場、他の出品者
- 出品機能
- 出品商品更新機能
- 出品取りやめ機能
- 管理ユーザー登録機能?
- 売買のためのログイン機能
- 商品およびユーザーの画像ファイルのアップロード機能
- DBテーブルのリレーション機能（ユーザー、商品、フォローしたユーザー、いいねした商品)
- DBトランザクション制御機能(更新や売買途中でエラー発生時にロールバックするようにしたい)
- ページネーション機能(1Pageに3*4ぐらいにしたい)
- 検索機能(本名で検索できる)
- 単体テスト機能
- 統合テスト機能

### できれば追加したい機能

- レコメンド機能(AWSマネージドサービスを利用したもの)?
- SNS認証(twiiter,google等)
- ソート機能



