# BPM Checker  
## 概要  
曲のBPMを測定するためのAndroidアプリケーションです。  
BPMを測定するとともに、タップの間隔とBPMをグラフで表示します  
  
## 使い方  
##### アプリ画面 
<img src="https://github.com/pikohan-suzuki/BPM_Checker/blob/images/Screenshot_1557749945.png" width="25%">
## 計測方法  
画面下部の"TAP HERE"を曲のテンポに合わせてタップします。 
複数回タップすると、左側にタップ間隔が横棒グラフで、右側にBPMが円で表示されます。タップ間隔の平均を用いて計測をしているため、タップする回数が多いほど精度が向上します。また、BPMは小数点以下を切り捨てているため、BPMの数値には誤差が生じます。    
## リセット  
画面左側の"RESET"と書かれたボタンを押すことで、今までの計測値・グラフをリセットすることができます。   
## セーブ・ホールド  
画面左側の"SAVE"を押すことで、計測値を一時的に保持することができます。保持している間は、"TAP HERE"ボタンを押しても、計測値が変動することはありません。計測値をストレージ内に保存してはいないため、アプリ再起動等の動作により値は削除されます。 
SAVE状態はリセットボタンを押すことで、解除することができます。

