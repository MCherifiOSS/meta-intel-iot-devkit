require iot-devkit-prof-image.bb

DESCRIPTION = "A fully functional image to be placed on an SD card with full profiling and dev tools"

IMAGE_INSTALL += "cmake"
IMAGE_INSTALL += "binutils"
IMAGE_INSTALL += "packagegroup-core-tools-profile"
IMAGE_INSTALL += "packagegroup-core-buildessential"
IMAGE_INSTALL += "git"
IMAGE_INSTALL += "mraa-doc"
IMAGE_INSTALL += "python-setuptools"
IMAGE_INSTALL += "i2c-tools"

IMAGE_FEATURES += "dev-pkgs tools-sdk"
EXTRA_IMAGE_FEATURES = "debug-tweaks"
