# FirstSpirit Module Editor Plugin for Eclipse

The plugin provides a from based editor for FirstSpirit5 `module.xml` files and basic refactoring support.  

## Installation

The plugin has been tested on Linux and Windows under Eclipse 4.5.1 (Mars),
older versions are not supported.

Use the following update site to install the plugin:

    https://dl.bintray.com/schocco/generic/
    
Or install using the Eclipse Marketplace (once the plugin is listed there).

<a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=2614946" class="drag" title="Drag to your running Eclipse workspace to install FirstSpirit Module-Descriptor Support"><img src="https://marketplace.eclipse.org/sites/all/themes/solstice/_themes/solstice_marketplace/public/images/btn-install.png" alt="Drag to your running Eclipse workspace to install FirstSpirit Module-Descriptor Support" /></a>

## Development

### IDE Setup
Make sure that you are using at least Eclipse Mars and that all Sapphire Features are installed in the IDE.

### Structure
The site project handles packaging all features and plugins into a repository.  
The built repository ends up in `site/target/repository`.

The fsModuleDescriptor sub-project contains the plugin which provides an editor and refactoring support.

## License
MIT License, the included firstspirit.png icon is &copy; e-Spirit AG
