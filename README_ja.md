# FastXPPickup

経験値オーブの収集遅延を除去または設定可能にするMinecraftプラグインです。パフォーマンスとプレイヤー体験の向上を目的としています。

## 特徴

- ✨ 経験値オーブの収集遅延を完全に除去または設定可能
- 🎯 XPオーブの値に基づいた動的遅延計算

## インストール

1. プラグインファイル（`FastXPPickup.jar`）をサーバーの `plugins` フォルダにコピー
2. サーバーを再起動またはプラグインをリロード
3. `plugins/FastXPPickup/config.yml` が自動生成されます

## 設定

### config.yml

```yaml
# 基本的なXPオーブ収集遅延（ティック単位、20ティック = 1秒）
# 0に設定すると遅延を完全に無効化
base-pickup-delay: 0

# XPオーブの値に基づいた遅延計算
value-based-delay:
  enabled: false              # 有効/無効
  delay-per-xp-point: 0.1    # XPポイントあたりの遅延（ティック）
  max-delay: 20              # 最大遅延上限（ティック）
  min-delay: 0               # 最小遅延下限（ティック）
```

### 設定例

#### 基本設定（遅延なし）
```yaml
base-pickup-delay: 0
value-based-delay:
  enabled: false
```

#### 固定遅延設定
```yaml
base-pickup-delay: 5  # 全てのXPオーブに5ティックの遅延
value-based-delay:
  enabled: false
```

#### 動的遅延設定
```yaml
base-pickup-delay: 0
value-based-delay:
  enabled: true
  delay-per-xp-point: 0.2  # XP 1ポイントにつき0.2ティック追加
  max-delay: 40           # 最大40ティック
  min-delay: 2            # 最小2ティック
```

## コマンド

### `/fastxp reload`
設定ファイルをリロードします。

**権限**: `fastxppickup.admin` (デフォルト: OP)

### '/fastxpp config'
設定ファイルの内容を表示します。

**権限**: `fastxppickup.admin` (デフォルト: OP)

## 権限

| 権限 | 説明 | デフォルト  |
|------|------|--------|
| `fastxppickup.pickup` | XPオーブを設定可能な遅延で収集 | 全プレイヤー |
| `fastxppickup.use` | `/fastxp` コマンドの使用 | OP     |
| `fastxppickup.admin` | 設定のリロード権限 | OP     |

## 動作原理

このプラグインは `PlayerExpCooldownChangeEvent` をフックして、XPオーブの収集遅延を制御します。

1. プレイヤーがXPオーブを収集しようとする
2. 近くのXPオーブの値を検出
3. 設定に基づいて遅延時間を計算
4. 計算された遅延を適用

### 遅延計算ロジック

```
値ベース遅延が有効な場合:
遅延 = XPオーブの値 × delay-per-xp-point
遅延 = Math.max(遅延, min-delay)
遅延 = Math.min(遅延, max-delay)

値ベース遅延が無効な場合:
遅延 = base-pickup-delay
```

## 互換性

- **Minecraft**: 1.19+
- **サーバーソフトウェア**: Bukkit/Spigot/Paper

## よくある質問

### Q: パフォーマンスへの影響は？
A: このプラグインは軽量で、XPオーブ収集時のみ動作します。通常のサーバー運用に支障をきたすことはありません。

### Q: 他のXP関連プラグインとの競合は？
A: 基本的に競合は発生しませんが、同様にXPオーブの収集遅延を制御するプラグインとは競合する可能性があります。

## トラブルシューティング

### 設定が反映されない
1. `/fastxp reload` コマンドで設定をリロード
2. `config.yml` の構文が正しいか確認
3. サーバーログでエラーをチェック

### 権限が機能しない
1. 権限プラグイン（LuckPerms等）の設定を確認
2. `/fastxp` コマンドでアクセス可能か確認

## 開発者情報

- **作者**: exstrasilly (xnnie)
- **連絡先**: 
  - Discord: @xxxxxxxxxnnie
  - Telegram: @xxxxxxxxxnnie
  - Fiverr: @exstramc

## 貢献

バグ報告や機能要求は歓迎します。プルリクエストを送信する前に、既存のコードスタイルに従ってください。

## 貢献リスト
- JF9TGL(Shimotuki Rieru): github @jf9tgl

---

**注意**: このプラグインを使用する前に、必ずサーバーのバックアップを取ることをお勧めします。
