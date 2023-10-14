import React from 'react';
import {SafeAreaView, StatusBar, StyleSheet} from 'react-native';
import Home from './src/Home';
import Home2 from './src/Home2';
function App(): JSX.Element {
  return (
    <SafeAreaView>
      <StatusBar barStyle={'dark-content'} backgroundColor={'white'} />
      {/* 桥接原生扫描模块 */}
      {/* <Home /> */}

      {/*  桥接原生扫描组件 */}
      <Home2 />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({});

export default App;
