# PiggyPearl - 掌上明猪

[![Vue-Version](https://img.shields.io/badge/Vue-2.96-brightgreen)](https://github.com/limisoul/PiggyPearl)
[![SpringBoot-Version](https://img.shields.io/badge/SpringBoot-2.7.9-brightgreen)](https://github.com/limisoul/PiggyPearl)
[![Python-Version](https://img.shields.io/badge/Python-3.8|3.9|3.10-blue)](https://github.com/limisoul/PiggyPearl)

这是一个基于神经网络的生猪发情识别的项目。

## News

* 2023.4.3: 创建仓库，包含pigWeb(电脑端)，pigMobile(手机端)，server(后端)。前端以Vue框架搭建，后端采用SpringBoot。

## Highlights

* **核心结构**: 最新Conformer声音识别算法，Transformer与CNN的超强融合，在不显著增加计算量的前提下显著提升了基网表征能力，为母猪发情监测提供精准识别结果。
* **可行性**: 由中国科学院大学联合鹏城实验室和华为提出，被ICCV2021录用。
* **轻量级**: 掌上明猪系统易于安装、便于使用、设计精良且文档齐全。
* **硬件优势**: 自研硬件数据采集系统，STM32H742系列能够提供2424 CoreMark /1027 DMIPS的性能，利用其L1缓存并实现了零等待执行，并且兼容OPEN-MV。
* **准确性**: 声音采集模块采用音频处理芯片LM286，对音频信号进行200倍放大，且灵敏度可调。
* **便携性**: 滑轨，拾听器，摄像头，机械臂多硬件一体化，便于安装拆卸，容易管理。

## Running

* 参阅[doc](docs)。
