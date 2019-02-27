
# cmake版本

cmake_minimum_required(VERSION 3.4.1)
# 添加库
add_library(
             native-lib
             SHARED
             src/main/cpp/native-lib.cpp )


find_library(
              abc
              log )


find_library(
              aaa
              z )


target_link_libraries(
                       native-lib

                       ${abc}
                        ${aaa})