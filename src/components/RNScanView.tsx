import {
  StyleSheet,
  Text,
  View,
  ViewProps,
  requireNativeComponent,
} from 'react-native';
import React from 'react';

type RNScanViewProps =
  | ViewProps
  | {
      onScanResult: (e: any) => void;
    };

type Props = {
  onQrcodeResult: (qrcode: string) => void;
};
// 获取原生组件
const NativeScanView = requireNativeComponent<RNScanViewProps>('ScanView');

export default ({onQrcodeResult}: Props) => {
  return (
    <NativeScanView
      style={styles.root}
      onScanResult={(e: any) => {
        const {qrcode} = e.nativeEvent;
        console.log('扫码结果', qrcode);
        onQrcodeResult(qrcode);
      }}
    />
  );
};

const styles = StyleSheet.create({
  root: {
    width: '100%',
    height: '100%',
  },
});
