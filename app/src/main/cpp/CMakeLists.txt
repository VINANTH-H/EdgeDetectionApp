cmake_minimum_required(VERSION 3.10.2)

project(edgedetection)

add_library(
        edgedetection
        SHARED
        image_processor.cpp
)

find_library(
        log-lib
        log
)

target_link_libraries(
        edgedetection
        ${log-lib}
)
